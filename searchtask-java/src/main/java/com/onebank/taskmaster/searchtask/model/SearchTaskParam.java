package com.onebank.taskmaster.searchtask.model;

import java.util.List;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class SearchTaskParam {
	@NotEmpty
	private String title;
	private List<@NotBlank String> tags;
}
