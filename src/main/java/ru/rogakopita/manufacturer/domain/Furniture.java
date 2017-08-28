package ru.rogakopita.manufacturer.domain;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;

import ru.rogakopita.manufacturer.domain.ifc.DTOIfc;

@SuppressWarnings("serial")
@Entity
public class Furniture implements DTOIfc, Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column
    private String name;

    @OneToOne
    private Department department;

    @Column
    @Enumerated(EnumType.STRING)
    private Type type;

    @Override
    public long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Department getDepartment() {
        return department;
    }

    public void setDepartment(Department department) {
        this.department = department;
    }

    public Type getType() {
        return type;
    }

    public void setType(Type type) {
        this.type = type;
    }

    enum Type {
        SOFT("Мягкая"), CORPS("Корпусная"), OFFICE("Офисная");

        private String name;

        private Type(String name) {
            this.setName(name);
        }

        public static Type byName(String name) throws Exception {
            if ("Мягкая".equals(name))
                return SOFT;
            if ("Корпусная".equals(name))
                return CORPS;
            if ("Офисная".equals(name))
                return Type.OFFICE;
            throw new Exception("Type unknown");
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }
    }
}
