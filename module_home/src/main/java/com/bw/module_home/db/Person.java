package com.bw.module_home.db;

import androidx.room.Entity;
import androidx.room.Ignore;
import androidx.room.PrimaryKey;

@Entity
public
/**
 *
 *

 ─────────────────────────────────────────────────────────────────────────
 ─████████──████████─██████████████─██████──────────██████─██████████████─
 ─██░░░░██──██░░░░██─██░░░░░░░░░░██─██░░██████████──██░░██─██░░░░░░░░░░██─
 ─████░░██──██░░████─██░░██████░░██─██░░░░░░░░░░██──██░░██─██░░██████████─
 ───██░░░░██░░░░██───██░░██──██░░██─██░░██████░░██──██░░██─██░░██─────────
 ───████░░░░░░████───██░░██████░░██─██░░██──██░░██──██░░██─██░░██─────────
 ─────████░░████─────██░░░░░░░░░░██─██░░██──██░░██──██░░██─██░░██──██████─
 ───────██░░██───────██░░██████░░██─██░░██──██░░██──██░░██─██░░██──██░░██─
 ───────██░░██───────██░░██──██░░██─██░░██──██░░██████░░██─██░░██──██░░██─
 ───────██░░██───────██░░██──██░░██─██░░██──██░░░░░░░░░░██─██░░██████░░██─
 ───────██░░██───────██░░██──██░░██─██░░██──██████████░░██─██░░░░░░░░░░██─
 ───────██████───────██████──██████─██████──────────██████─██████████████─
 ─────────────────────────────────────────────────────────────────────────
 *
 **/
class Person {

   @PrimaryKey(autoGenerate = true)
   private Long id;

   private String name;

   private String age;

   @Ignore
   public Person() {
   }

   public Person(Long id, String name, String age) {
      this.id = id;
      this.name = name;
      this.age = age;
   }

   public Long getId() {
      return id;
   }

   public void setId(Long id) {
      this.id = id;
   }

   public String getName() {
      return name;
   }

   public void setName(String name) {
      this.name = name;
   }

   public String getAge() {
      return age;
   }

   public void setAge(String age) {
      this.age = age;
   }
}
