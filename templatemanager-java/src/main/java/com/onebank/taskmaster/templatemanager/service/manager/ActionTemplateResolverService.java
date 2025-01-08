package com.onebank.taskmaster.templatemanager.service.manager;

import com.google.inject.Inject;
import com.onebank.taskmaster.templatemanager.model.SubEventType;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor(onConstructor = @__({@Inject}))
public class ActionTemplateResolverService implements ActionTemplateResolver {
    private final CreateOrUpdateTemplate createOrUpdateTemplate;
    private final DeleteTemplate deleteTemplate;

    @Override
    public ActionTemplate resolve(SubEventType subEventType) {
        return switch (subEventType) {
            case OBJECT_FINALIZED -> createOrUpdateTemplate;
            case OBJECT_DELETED -> deleteTemplate;
            default -> throw new UnsupportedOperationException();
        };
    }
}
