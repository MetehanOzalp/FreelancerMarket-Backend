package GraduationProject.freelancermarket.entities;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
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
@Table(name = "advert_comments")
@PrimaryKeyJoinColumn(name = "comment_id")
@JsonIgnoreProperties({ "hibernateLazyInitializer", "handler", "advert" })
@AllArgsConstructor
@NoArgsConstructor
public class AdvertComment extends Comment {

	@Column(name = "advert_id")
	private int advertId;

	@Column(name = "score")
	private Double score;

	@ManyToOne
	@JoinColumn(name = "advert_id", insertable = false, updatable = false)
	private Advert advert;

	@OneToMany(mappedBy = "advertComment")
	private List<AdvertCommentResponse> advertCommentResponses;

}
