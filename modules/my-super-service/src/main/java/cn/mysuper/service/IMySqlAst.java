package cn.mysuper.service;

import java.util.List;

/**
 * 调用 smart 中的方法
 * */
public interface IMySqlAst {

    public List lineToList(final String line);

    public List getSmartSegment(final String line);

    public List reSmartSegmentLst(final List lst);

    public Object sqlToAst(final List lst);

    public String getStrValue(final String line);
}
