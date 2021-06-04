package com.imooc.pojo;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
public class JPATest {
    @Id
    private Integer id;

    @Column(name = "jpa_name")
    private String jpaname;

    @Override
    public String toString() {
        return "JPATest{" +
                "id=" + id +
                ", jpaname='" + jpaname + '\'' +
                '}';
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getJpaname() {
        return jpaname;
    }

    public void setJpaname(String jpaname) {
        this.jpaname = jpaname;
    }
}
