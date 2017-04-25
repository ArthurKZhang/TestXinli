package com.arthur;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.apache.struts2.dispatcher.filter.StrutsPrepareAndExecuteFilter;

import javax.servlet.*;
import java.io.IOException;

/**
 * Created by zhangyu on 18/04/2017.
 */
public class BaseFilter extends StrutsPrepareAndExecuteFilter {
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        super.init(filterConfig);
//        final Logger log = LogManager;//Logger.getLogger(getClass());
//        Logger logger = LogManager.getLogger();
    }

    @Override
    public void destroy() {
        super.destroy();
    }
}
