package models;

import modules.preloader.DatabasePreloader;
import org.hibernate.annotations.OrderBy;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

import static org.apache.commons.lang3.RandomStringUtils.randomAlphabetic;
import static org.apache.commons.lang3.text.WordUtils.capitalizeFully;

@Entity
@Table(name = "people")
public class Person {
    @Id
    @GeneratedValue
    @Column(name = "person_id")
    private long id;

    @Column(name = "first_name", nullable = false)
    private String firstName;

    @Column(name = "middle_name")
    private String middleName;

    @Column(name = "last_name", nullable = false)
    private String lastName;

    @Column(name = "display_name", nullable = false)
    private String displayName;

    @OneToOne(fetch = FetchType.LAZY)
    private File photo;

    @OneToMany(mappedBy = "person", fetch = FetchType.LAZY, cascade = CascadeType.ALL, orphanRemoval = true)
    @OrderBy(clause = "type")
    private List<PersonInfo> info = new ArrayList<>();

    @ManyToMany(mappedBy = "members", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private List<Group> memberOf = new ArrayList<>();

    @OneToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(nullable = false, unique = true)
    private Group selfGroup;

    @OneToMany(mappedBy = "author", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private List<Message> messages = new ArrayList<>();

    @ManyToMany(mappedBy = "tags", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private List<Message> taggedIn = new ArrayList<>();

    @ManyToMany(mappedBy = "upvotes", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private List<Message> upvotesIn = new ArrayList<>();

    @ManyToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinTable(
            name = "friends",
            joinColumns = {@JoinColumn(name = "person_id")},
            inverseJoinColumns = {@JoinColumn(name = "friend_id")}
    )
    private List<Person> friends = new ArrayList<>();

    @ManyToMany(mappedBy = "friends", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private List<Person> friendOf = new ArrayList<>();

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getMiddleName() {
        return middleName;
    }

    public void setMiddleName(String middleName) {
        this.middleName = middleName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getDisplayName() {
        return displayName;
    }

    public void setDisplayName(String displayName) {
        this.displayName = displayName;
    }

    public List<PersonInfo> getInfo() {
        return info;
    }

    public List<Group> getMemeberOf() {
        return memberOf;
    }

    public File getPhoto() {
        return photo;
    }

    public void setPhoto(File photo) {
        this.photo = photo;
    }

    public Group getSelfGroup() {
        return selfGroup;
    }

    public void setSelfGroup(Group selfGroup) {
        this.selfGroup = selfGroup;
    }

    public List<Message> getMessages() {
        return messages;
    }

    public List<Message> getTaggedIn() {
        return taggedIn;
    }

    static {
        DatabasePreloader.addTest((em -> {
            for (int i = 0; i < 100; i++) {
                String firstName = capitalizeFully(randomAlphabetic(10));
                String lastName = capitalizeFully(randomAlphabetic(10));
                new PersonFactory(em)
                        .setFirstName(firstName)
                        .setLastName(lastName)
                        .setDisplayName(firstName + " " + lastName)
                        .build();
            }
        }), 10);
    }
}
