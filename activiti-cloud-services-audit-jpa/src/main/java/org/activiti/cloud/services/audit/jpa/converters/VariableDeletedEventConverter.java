package org.activiti.cloud.services.audit.jpa.converters;

import org.activiti.api.model.shared.event.VariableEvent;
import org.activiti.cloud.api.model.shared.events.CloudRuntimeEvent;
import org.activiti.cloud.api.model.shared.events.CloudVariableDeletedEvent;
import org.activiti.cloud.api.model.shared.impl.events.CloudRuntimeEventImpl;
import org.activiti.cloud.api.model.shared.impl.events.CloudVariableDeletedEventImpl;
import org.activiti.cloud.services.audit.jpa.events.AuditEventEntity;
import org.activiti.cloud.services.audit.jpa.events.VariableDeletedEventEntity;

public class VariableDeletedEventConverter extends BaseEventToEntityConverter {

    public VariableDeletedEventConverter(EventContextInfoAppender eventContextInfoAppender) {
        super(eventContextInfoAppender);
    }
    
    @Override
    public String getSupportedEvent() {
        return VariableEvent.VariableEvents.VARIABLE_DELETED.name();
    }

    @Override
    protected VariableDeletedEventEntity createEventEntity(CloudRuntimeEvent cloudRuntimeEvent) {
        CloudVariableDeletedEvent cloudVariableDeletedEvent = (CloudVariableDeletedEvent) cloudRuntimeEvent;
        return new VariableDeletedEventEntity(cloudVariableDeletedEvent.getId(),
                                              cloudVariableDeletedEvent.getTimestamp(),
                                              cloudVariableDeletedEvent.getAppName(),
                                              cloudVariableDeletedEvent.getAppVersion(),
                                              cloudVariableDeletedEvent.getServiceFullName(),
                                              cloudVariableDeletedEvent.getServiceName(),
                                              cloudVariableDeletedEvent.getServiceType(),
                                              cloudVariableDeletedEvent.getServiceVersion(),
                                              cloudVariableDeletedEvent.getMessageId(),
                                              cloudVariableDeletedEvent.getSequenceNumber(),
                                              cloudVariableDeletedEvent.getEntity());
        
    }

    @Override
    protected CloudRuntimeEventImpl<?, ?> createAPIEvent(AuditEventEntity auditEventEntity) {
        VariableDeletedEventEntity variableDeletedEventEntity = (VariableDeletedEventEntity) auditEventEntity;

        CloudVariableDeletedEventImpl variableDeletedEvent = new CloudVariableDeletedEventImpl(variableDeletedEventEntity.getEventId(),
                                                                                               variableDeletedEventEntity.getTimestamp(),
                                                                                               variableDeletedEventEntity.getVariableInstance());
        variableDeletedEvent.setAppName(variableDeletedEventEntity.getAppName());
        variableDeletedEvent.setAppVersion(variableDeletedEventEntity.getAppVersion());
        variableDeletedEvent.setServiceFullName(variableDeletedEventEntity.getServiceFullName());
        variableDeletedEvent.setServiceName(variableDeletedEventEntity.getServiceName());
        variableDeletedEvent.setServiceType(variableDeletedEventEntity.getServiceType());
        variableDeletedEvent.setServiceVersion(variableDeletedEventEntity.getServiceVersion());
        variableDeletedEvent.setMessageId(variableDeletedEventEntity.getMessageId());
        variableDeletedEvent.setSequenceNumber(variableDeletedEventEntity.getSequenceNumber());
        
        variableDeletedEvent.setProcessDefinitionId(variableDeletedEventEntity.getProcessDefinitionId());

        return variableDeletedEvent;
    }
}
