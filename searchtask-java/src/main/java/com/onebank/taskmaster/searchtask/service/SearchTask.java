package com.onebank.taskmaster.searchtask.service;

import com.onebank.taskmaster.searchtask.model.SearchTaskParam;
import com.onebank.taskmaster.searchtask.model.SearchTaskResponse;
import lombok.NonNull;

public interface SearchTask {
	SearchTaskResponse search(@NonNull SearchTaskParam param);
}
