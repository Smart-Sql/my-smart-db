package org.gridgain.myservice;

import cn.mysuper.service.IMySqlAst;
import org.gridgain.plus.dml.MyLexical;
import org.gridgain.plus.dml.MySelectPlus;
import org.gridgain.plus.dml.MySmartSql;

import java.util.ArrayList;
import java.util.List;

public class MySqlAstImpl implements IMySqlAst {
    @Override
    public List lineToList(String line) {
        return MyLexical.lineToList(line);
    }

    @Override
    public List getSmartSegment(String line) {
        return MySmartSql.getSmartSegment(line);
    }

    @Override
    public List reSmartSegmentLst(List lst) {
        return MySmartSql.reSmartSegmentLst(lst);
    }

    @Override
    public Object sqlToAst(List lst) {
        return  MySelectPlus.sqlToAst((ArrayList) lst);
    }

    @Override
    public String getStrValue(String line) {
        return MyLexical.getStrValue(line);
    }
}
