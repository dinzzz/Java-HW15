
package hr.fer.zemris.java.tecaj_13.model;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

/**
 * Class that represents a blog user. Blog user can register on this web
 * application and then manage his own blog by creating and editing
 * entries/posts.
 * 
 * @author Dinz
 *
 */
@Entity
@Table(name = "blog_users")
public class BlogUser {

	/**
	 * Blog user database ID.
	 */
	private Long id;

	/**
	 * Blog user's first name-
	 */
	private String firstName;

	/**
	 * Blog user's last name.
	 */
	private String lastName;

	/**
	 * Blog user's nickname.
	 */
	private String nick;

	/**
	 * Blog user's email.
	 */
	private String email;

	/**
	 * Hash SHA representation of the user's password.
	 */
	private String passwordHash;

	/**
	 * List of user's entries.
	 */
	private List<BlogEntry> entries;

	/**
	 * Gets the user's database ID.
	 * 
	 * @return User's ID.
	 */
	@Id
	@GeneratedValue
	public Long getId() {
		return id;
	}

	/**
	 * Sets the user's database ID:
	 * 
	 * @param id
	 *            ID to be set.
	 */
	public void setId(Long id) {
		this.id = id;
	}

	/**
	 * Gets the user's first name.
	 * 
	 * @return User's first name.
	 */
	@Column(nullable = false, length = 30)
	public String getFirstName() {
		return firstName;
	}

	/**
	 * Sets the user's first name.
	 * 
	 * @param firstName
	 *            Name to be set.
	 */
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	/**
	 * Gets the user's last name.
	 * 
	 * @return User's last name.
	 */
	@Column(nullable = false, length = 50)
	public String getLastName() {
		return lastName;
	}

	/**
	 * Sets the user's last name.
	 * 
	 * @param lastName
	 *            Last name to be set.
	 */
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	/**
	 * Gets the user's nickname.
	 * 
	 * @return User's nickname.
	 */
	@Column(nullable = false, length = 20, unique = true)
	public String getNick() {
		return nick;
	}

	/**
	 * Sets the user's nickname.
	 * 
	 * @param nick
	 *            Nickname to be set.
	 */
	public void setNick(String nick) {
		this.nick = nick;
	}

	/**
	 * Gets user's email.
	 * 
	 * @return User's email.
	 */
	@Column(nullable = false, length = 50, unique = true)
	public String getEmail() {
		return email;
	}

	/**
	 * Sets user's email.
	 * 
	 * @param email
	 *            Email to be set.
	 */
	public void setEmail(String email) {
		this.email = email;
	}

	/**
	 * Gets the hash SHA representation of the user's password.
	 * 
	 * @return User's hashed password.
	 */
	@Column(nullable = false)
	public String getPasswordHash() {
		return passwordHash;
	}

	/**
	 * Sets the hashed representation of the user's password.
	 * 
	 * @param passwordHash
	 *            Hashed password.
	 */
	public void setPasswordHash(String passwordHash) {
		this.passwordHash = passwordHash;
	}

	/**
	 * Gets the list of user's blog entries.
	 * 
	 * @return User's blog entries.
	 */
	@OneToMany(mappedBy = "author")
	public List<BlogEntry> getEntries() {
		return entries;
	}

	/**
	 * Sets user's blog entries.
	 * 
	 * @param entries
	 *            Entries to be set.
	 */
	public void setEntries(List<BlogEntry> entries) {
		this.entries = entries;
	}

}
