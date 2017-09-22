package com.webstar.filters;

import java.util.regex.Pattern;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletRequestWrapper;

import org.apache.log4j.Logger;
import org.jsoup.Jsoup;
import org.jsoup.safety.Whitelist;
import org.owasp.esapi.ESAPI;

public class XssRequestWrapper extends HttpServletRequestWrapper
{
    private static final Logger logger = Logger.getLogger(XssRequestWrapper.class);

    public XssRequestWrapper(HttpServletRequest httpServletRequest)
    {
        super(httpServletRequest);
    }

    @Override
    public String[] getParameterValues(String parameter)
    {
        final String[] values = super.getParameterValues(parameter);

        if (values == null) {
            return null;
        }
        final int count = values.length;
        final String[] encodedValues = new String[count];
        for (int i = 0; i < count; i++) {
            encodedValues[i] = stripXSS(parameter + "[" + i + "]", values[i]);
        }
        return encodedValues;
    }

    @Override
    public String getParameter(String parameter)
    {
        String value = super.getParameter(parameter);
        if (value != null) {

            value = stripXSS(parameter, value);
        }
        return value;
    }

    @Override
    public String getHeader(String name)
    {
        final String value = super.getHeader(name);
        return stripXSS(name, value);
    }

    private String stripXSS(String name, String value)
    {
        if (value != null) {
            final String before = value;

            try {
                value = ESAPI.encoder().canonicalize(value);
                value = value.replaceAll("\0", "");
                value = Jsoup.clean(value, Whitelist.none());

                value = FallBackSQLStripXSS(value);

            } catch (final Exception e) {
                logger.error("[XSSFilter] Error executing XSS Filter through api - executing FallbackStripXSS"
                    + e.getMessage(), e);

                value = FallBackStripXSS(value);
            }

            if (!before.equals(value)) {
                logger.info(String.format("[XSSRequest] %s = %s --> %s", name, before, value));
            }
        }
        return value;
    }

    private String FallBackSQLStripXSS(String value)
    {
        // Avoid Select expressions
        Pattern scriptPattern =
            Pattern.compile("select\\s(.*?)", Pattern.CASE_INSENSITIVE | Pattern.MULTILINE | Pattern.DOTALL);
        value = scriptPattern.matcher(value).replaceAll("");

        // Avoid Update expressions
        scriptPattern =
            Pattern.compile("update\\s(.*?)", Pattern.CASE_INSENSITIVE | Pattern.MULTILINE | Pattern.DOTALL);
        value = scriptPattern.matcher(value).replaceAll("");

        // Avoid Insert expressions
        scriptPattern =
            Pattern.compile("insert\\s(.*?)", Pattern.CASE_INSENSITIVE | Pattern.MULTILINE | Pattern.DOTALL);
        value = scriptPattern.matcher(value).replaceAll("");

        // Avoid Delete expressions
        scriptPattern =
            Pattern.compile("delete\\s(.*?)", Pattern.CASE_INSENSITIVE | Pattern.MULTILINE | Pattern.DOTALL);
        value = scriptPattern.matcher(value).replaceAll("");

        return value;
    }

    private String FallBackStripXSS(String value)
    {
        // Avoid anything between script tags
        Pattern scriptPattern = Pattern.compile("<script>(.*?)</script>", Pattern.CASE_INSENSITIVE);
        value = scriptPattern.matcher(value).replaceAll("");

        // Avoid anything in a src='...' type of expression
        scriptPattern =
            Pattern.compile("src[\r\n]*=[\r\n]*\\\'(.*?)\\\'", Pattern.CASE_INSENSITIVE | Pattern.MULTILINE
                | Pattern.DOTALL);
        value = scriptPattern.matcher(value).replaceAll("");

        scriptPattern =
            Pattern.compile("src[\r\n]*=[\r\n]*\\\"(.*?)\\\"", Pattern.CASE_INSENSITIVE | Pattern.MULTILINE
                | Pattern.DOTALL);
        value = scriptPattern.matcher(value).replaceAll("");

        // Remove any lonesome </script> tag
        scriptPattern = Pattern.compile("</script>", Pattern.CASE_INSENSITIVE);
        value = scriptPattern.matcher(value).replaceAll("");

        // Remove any lonesome <script ...> tag
        scriptPattern = Pattern.compile("<script(.*?)>", Pattern.CASE_INSENSITIVE | Pattern.MULTILINE | Pattern.DOTALL);
        value = scriptPattern.matcher(value).replaceAll("");

        // Avoid eval(...) expressions
        scriptPattern =
            Pattern.compile("eval\\((.*?)\\)", Pattern.CASE_INSENSITIVE | Pattern.MULTILINE | Pattern.DOTALL);
        value = scriptPattern.matcher(value).replaceAll("");

        // Avoid expression(...) expressions
        scriptPattern =
            Pattern.compile("expression\\((.*?)\\)", Pattern.CASE_INSENSITIVE | Pattern.MULTILINE | Pattern.DOTALL);
        value = scriptPattern.matcher(value).replaceAll("");

        // Avoid javascript:... expressions
        scriptPattern = Pattern.compile("javascript:", Pattern.CASE_INSENSITIVE);
        value = scriptPattern.matcher(value).replaceAll("");

        // Avoid vbscript:... expressions
        scriptPattern = Pattern.compile("vbscript:", Pattern.CASE_INSENSITIVE);
        value = scriptPattern.matcher(value).replaceAll("");

        // Avoid onload= expressions
        scriptPattern = Pattern.compile("onload(.*?)=", Pattern.CASE_INSENSITIVE | Pattern.MULTILINE | Pattern.DOTALL);
        value = scriptPattern.matcher(value).replaceAll("");

        return value;
    }

}
