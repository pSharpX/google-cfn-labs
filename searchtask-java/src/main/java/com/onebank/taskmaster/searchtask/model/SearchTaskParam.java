package com.onebank.taskmaster.searchtask.model;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class SearchTaskParam {
	private String title;
	private List<String> tags;
}
