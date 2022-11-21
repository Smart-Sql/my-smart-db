package org.apache.ignite.configuration;

import java.io.Serializable;

public class TableTemplateConfiguration implements Serializable {
    private static final long serialVersionUID = 1793228331950175255L;

    private String templateValue;
    private String description;

    public String getTemplateValue() {
        return templateValue;
    }

    public void setTemplateValue(String templateValue) {
        this.templateValue = templateValue;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @Override
    public String toString() {
        return "TableTemplateConfiguration{" +
                "templateValue='" + templateValue + '\'' +
                ", description='" + description + '\'' +
                '}';
    }
}
