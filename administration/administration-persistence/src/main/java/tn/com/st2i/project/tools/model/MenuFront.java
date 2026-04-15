package tn.com.st2i.project.tools.model;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class MenuFront {

	private Long id;
	private String label;
	private String icon;
	private String link;
	private List<MenuFront> subItems;
	private Boolean isTitle;
	private String badge;
	private Long parentId;
	private Boolean isLayout;

}
