package com.onebank.taskmaster.searchtask.repository;

import org.apache.ibatis.jdbc.SQL;

public class SqlUtils {
    public String getTagsByTaskId(Long taskId) {
        return new SQL() {{
            SELECT("tg.id, tg.name");
            FROM("tasks_tags tt");
            INNER_JOIN("tags tg ON tg.id = tt.tag_id");
            WHERE("tt.task_id = #{taskId}");
        }}.toString();
    }
}
