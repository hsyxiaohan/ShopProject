package com.bw.library_stroage;

import android.util.Log;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.RawQuery;
import androidx.room.Update;
import androidx.sqlite.db.SimpleSQLiteQuery;
import androidx.sqlite.db.SupportSQLiteQuery;

import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.List;
import java.util.logging.Logger;

@Dao
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
abstract class BaseDatabase<T> {

    private static final String TAG = "DataBeanDao";

    private String tableName;

    //构造时 通过反射获取表名
    public BaseDatabase() {

        ParameterizedType parameterizedType = (ParameterizedType) getClass().getSuperclass().getGenericSuperclass();

        Class tableNameClass = (Class) parameterizedType.getActualTypeArguments()[0];

        tableName  = tableNameClass.getSimpleName();

        Log.i(TAG, "BaseDatabase: "+tableName);
    }

    public void insertData(T... bean){
        insert(bean);
    }




    @Insert
    protected abstract void insert(T... bean);



    public void upDataData(T... bean){
        upData(bean);
    }

    @Update
    protected abstract void upData(T... bean);


    public void DeleteData(T... bean){
        delete(bean);
    }

    @Delete
    protected abstract void delete(T... bean);

    //查询单个
    @RawQuery
    protected abstract T find(SupportSQLiteQuery query);

    public T find(Long id){
        SimpleSQLiteQuery query = new SimpleSQLiteQuery(" SELECT * FROM " + tableName + " WHERE id = ? ", new Long[]{id});
        return find(query);
    }

    //    base模糊查询
    @RawQuery
    protected abstract List<T> fuzzyFind(SupportSQLiteQuery query);


    //删除所有
    @RawQuery
    protected abstract Integer deleteAll(SupportSQLiteQuery query);

    public Integer deleteAll(){
        SimpleSQLiteQuery query = new SimpleSQLiteQuery(" DELETE FROM "+tableName);
        return deleteAll(query);
    }


    //查询所有
    @RawQuery
    protected abstract List<T> findAll(SupportSQLiteQuery querys);

    public List<T> findAll(){
        SimpleSQLiteQuery query = new SimpleSQLiteQuery(" SELECT * FROM "+tableName);
        return findAll(query);
    }


}
