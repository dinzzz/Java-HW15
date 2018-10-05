package hr.fer.zemris.java.tecaj_13.model;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.Cacheable;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.OrderBy;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 * Class that represents a blog entry model in the web application. This is
 * usually refered to as a blog article rather then an entry. On this web
 * application, each user can edit or add new entries on his own blog page.
 * 
 * @author Dinz
 *
 */
@NamedQueries({
		@NamedQuery(name = "BlogEntry.upit1", query = "select b from BlogComment as b where b.blogEntry=:be and b.postedOn>:when") })
@Entity
@Table(name = "blog_entries")
@Cacheable(true)
public class BlogEntry {
	
	/**
	 * Blog entry database ID.
	 */
	private Long id;
	
	/**
	 * List of comments on the entry.
	 */
	private List<BlogComment> comments = new ArrayList<>();
	
	/**
	 * Date when the entry was created.
	 */
	private Date createdAt;
	
	/**
	 * Date when the entry was last modified.
	 */
	private Date lastModifiedAt;
	
	/**
	 * Title of the entry.
	 */
	private String title;
	
	/**
	 * Text of the entry.
	 */
	private String text;
	
	/**
	 * Author of the entry.
	 */
	private BlogUser author;
	
	/**
	 * Gets the blog entry database ID.
	 * @return blog entry ID.
	 */
	@Id
	@GeneratedValue
	public Long getId() {
		return id;
	}
	
	/**
	 * Sets the blog entry ID.
	 * @param id ID to be set.
	 */
	public void setId(Long id) {
		this.id = id;
	}
	
	/**
	 * Gets the list of the entry comments.
	 * @return List of the comments.
	 */
	@OneToMany(mappedBy = "blogEntry", fetch = FetchType.LAZY, cascade = CascadeType.PERSIST, orphanRemoval = true)
	@OrderBy("postedOn")
	public List<BlogComment> getComments() {
		return comments;
	}
	
	/**
	 * Sets the list of the entry comments.
	 * @param comments Comments to be set.
	 */
	public void setComments(List<BlogComment> comments) {
		this.comments = comments;
	}
	
	/**
	 * Gets the time of entry creation.
	 * @return Time when the entry was created.
	 */
	@Temporal(TemporalType.TIMESTAMP)
	@Column(nullable = false)
	public Date getCreatedAt() {
		return createdAt;
	}
	
	/**
	 * Sets the time of the entry creation.
	 * @param createdAt Time to be set.
	 * 
	 */
	public void setCreatedAt(Date createdAt) {
		this.createdAt = createdAt;
	}
	
	/**
	 * Gets the time of the last entry modification.
	 * @return Time of the last entry modification.
	 */
	@Temporal(TemporalType.TIMESTAMP)
	@Column(nullable = true)
	public Date getLastModifiedAt() {
		return lastModifiedAt;
	}
	
	/**
	 * Sets the time of the last entry modification.
	 * @param lastModifiedAt Time to be set.
	 */
	public void setLastModifiedAt(Date lastModifiedAt) {
		this.lastModifiedAt = lastModifiedAt;
	}
	
	/**
	 * Gets the entry title.
	 * @return Entry title.
	 */
	@Column(length = 200, nullable = false)
	public String getTitle() {
		return title;
	}
	
	/**
	 * Sets the entry title.
	 * @param title Title to be set.
	 */
	public void setTitle(String title) {
		this.title = title;
	}
	
	/**
	 * Gets the entry text.
	 * @return Entry text.
	 */
	@Column(length = 4096, nullable = false)
	public String getText() {
		return text;
	}
	
	/**
	 * Sets the entry text.
	 * @param text Text to be set.
	 */
	public void setText(String text) {
		this.text = text;
	}
	
	/**
	 * Gets the author of the entry.
	 * @return Author of the entry.
	 */
	@ManyToOne
	@JoinColumn(nullable = false)
	public BlogUser getAuthor() {
		return author;
	}
	
	/**
	 * Sets the author of the entry.
	 * @param author Author to be set.
	 */
	public void setAuthor(BlogUser author) {
		this.author = author;
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
		BlogEntry other = (BlogEntry) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}
}