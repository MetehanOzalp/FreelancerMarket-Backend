package GraduationProject.freelancermarket.entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Data
@EqualsAndHashCode(callSuper = false)
@Entity
@Table(name = "advert_comment_responses")
@PrimaryKeyJoinColumn(name = "comment_id")
@JsonIgnoreProperties({ "hibernateLazyInitializer", "handler", "advertComment" })
@AllArgsConstructor
@NoArgsConstructor
public class AdvertCommentResponse extends Comment {

	@Column(name = "advert_comment_id")
	private int advertCommentId;

	@ManyToOne
	@JoinColumn(name = "advert_comment_id", insertable = false, updatable = false)
	private AdvertComment advertComment;

}
