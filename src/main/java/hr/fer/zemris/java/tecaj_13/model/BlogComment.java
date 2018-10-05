package hr.fer.zemris.java.tecaj_13.model;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 * Class that represents a model of a blog comment. Blog comment is a classic
 * part of basic blog structures on the internet where a person can share their
 * opinion of a certain article.
 * 
 * @author Dinz
 *
 */
@Entity
@Table(name = "blog_comments")
public class BlogComment {

	/**
	 * Blog ID in the database-
	 */
	private Long id;

	/**
	 * Blog article where this comment is written.
	 */
	private BlogEntry blogEntry;

	/**
	 * Email of the comment's author.
	 */
	private String usersEMail;

	/**
	 * Text of the comment.
	 */
	private String message;

	/**
	 * Time where the comment was posted.
	 */
	private Date postedOn;

	/**
	 * Gets the ID of the comment in the database.
	 * 
	 * @return Comment ID.
	 */
	@Id
	@GeneratedValue
	public Long getId() {
		return id;
	}

	/**
	 * Sets the comment ID.
	 * 
	 * @param id
	 *            ID to be set.
	 */
	public void setId(Long id) {
		this.id = id;
	}

	/**
	 * Gets the blog entry where the comment is posted.
	 * 
	 * @return Blog entry.
	 */
	@ManyToOne
	@JoinColumn(nullable = false)
	public BlogEntry getBlogEntry() {
		return blogEntry;
	}

	/**
	 * Sets the blog entry where the comment is posted.
	 * 
	 * @param blogEntry
	 *            Blog entry to be set.
	 */
	public void setBlogEntry(BlogEntry blogEntry) {
		this.blogEntry = blogEntry;
	}

	/**
	 * Gets the email of the author of the comment.
	 * 
	 * @return Email of the comment author.
	 */
	@Column(length = 100, nullable = false)
	public String getUsersEMail() {
		return usersEMail;
	}

	/**
	 * Sets the email of the comment author.
	 * 
	 * @param usersEMail
	 *            Email to be set.
	 */
	public void setUsersEMail(String usersEMail) {
		this.usersEMail = usersEMail;
	}

	/**
	 * Gets the text of the comment.
	 * 
	 * @return Text of the comment.
	 */
	@Column(length = 4096, nullable = false)
	public String getMessage() {
		return message;
	}

	/**
	 * Sets the text of the comment.
	 * 
	 * @param message
	 *            Text to be set.
	 */
	public void setMessage(String message) {
		this.message = message;
	}

	/**
	 * Gets the date when the comment was posted.
	 * 
	 * @return Date when the comment was posted.
	 */
	@Temporal(TemporalType.TIMESTAMP)
	@Column(nullable = false)
	public Date getPostedOn() {
		return postedOn;
	}

	/**
	 * sets the date when the comment was posted.
	 * 
	 * @param postedOn
	 *            Date to be set.
	 */
	public void setPostedOn(Date postedOn) {
		this.postedOn = postedOn;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		BlogComment other = (BlogComment) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}
}