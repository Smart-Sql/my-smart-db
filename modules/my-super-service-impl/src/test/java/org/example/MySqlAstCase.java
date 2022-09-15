package org.example;

import cn.myservice.MySqlAstService;
import cn.mysuper.service.IMySqlAst;
import org.junit.Test;

import java.util.List;

public class MySqlAstCase {

    @Test
    public void myAstCase_1()
    {
        IMySqlAst mySqlAst = MySqlAstService.getInstance().getMySqlAst();
        List<String> lst = mySqlAst.lineToList("let a = 1+2; let b = 12;");
        for (String row : lst)
        {
            System.out.println(row);
        }
    }

    @Test
    public void myAstCase()
    {
        IMySqlAst mySqlAst = MySqlAstService.getInstance().getMySqlAst();
        List<List<String>> lst = mySqlAst.getSmartSegment("let a = 1+2; let b = 12;");
        for (List<String> row : lst)
        {
            System.out.println(row);
        }
    }

    @Test
    public void myAstCase_func()
    {
        IMySqlAst mySqlAst = MySqlAstService.getInstance().getMySqlAst();
        Object ast = mySqlAst.sqlToAst(mySqlAst.lineToList("f(a, b+c, g(q+w, e-r))"));
        System.out.println(ast);
    }

}
