package com.dynamicemployeemanagementsystem.common.enums;

import com.dynamicemployeemanagementsystem.common.constants.Msg;
import com.dynamicemployeemanagementsystem.common.exceptions.EnumNotFoundException;
import com.dynamicemployeemanagementsystem.common.utilities.ExceptionUtil;

public enum RoleEnum {
    ROLE_ADMIN(1, "Admin", "ROLE_ADMIN", "Role Admin"),
    ROLE_USER(2, "Employee", "ROLE_EMPLOYEE", "Role Employee");

    private Integer value;
    private String label;
    private String name;
    private String description;

    RoleEnum(Integer value, String label, String name, String description) {
        this.value = value;
        this.label = label;
        this.name = name;
        this.description = description;
    }

    public Integer getValue() {
        return value;
    }

    public void setValue(Integer value) {
        this.value = value;
    }

    public String getLabel() {
        return label;
    }

    public void setLabel(String label) {
        this.label = label;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public static RoleEnum getByLabel(String label) throws EnumNotFoundException {
        for (RoleEnum roleEnum : RoleEnum.values()) {
            if (roleEnum.label.equalsIgnoreCase(label))
                return roleEnum;
        }
        throw ExceptionUtil.getEnumNotFoundException(Msg.Enum.ROLE_ENUM);
    }
}
