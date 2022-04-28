package org.huazi.note.word;

import freemarker.cache.ClassTemplateLoader;
import freemarker.cache.NullCacheStorage;
import freemarker.template.Configuration;
import freemarker.template.Template;
import freemarker.template.TemplateExceptionHandler;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.BufferedWriter;
import java.io.IOException;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.nio.charset.StandardCharsets;
import java.util.HashMap;
import java.util.Map;

public class FreemarkerUtils {
    private static final Logger log = LoggerFactory.getLogger(FreemarkerUtils.class);
    private Configuration configuration;
    private String prefix;
    private ThreadLocal<Template> currentTemplate = new ThreadLocal();
    private static final Map<String, Template> TEMPLATE_CACHE = new HashMap();

    private FreemarkerUtils(Class<?> sourceClass, String templatePath) {
        this.configuration = new Configuration(Configuration.VERSION_2_3_22);
        this.prefix = sourceClass.getName() + ":";
        this.configuration.setTemplateLoader(new ClassTemplateLoader(sourceClass, templatePath));
        this.configuration.setDefaultEncoding("UTF-8");
        this.configuration.setTemplateExceptionHandler(TemplateExceptionHandler.RETHROW_HANDLER);
        this.configuration.setCacheStorage(NullCacheStorage.INSTANCE);
    }

    public static FreemarkerUtils build(Class<?> sourceClass, String templatePath) {
        log.debug("The template path is: {}", templatePath);
        return new FreemarkerUtils(sourceClass, templatePath);
    }

    public FreemarkerUtils setTemplate(String templateName) {
        try {
            String cacheKey = this.prefix + templateName;
            Template template = TEMPLATE_CACHE.get(cacheKey);
            if (template == null) {
                template = this.configuration.getTemplate(templateName);
                TEMPLATE_CACHE.put(cacheKey, template);
            }

            this.currentTemplate.set(template);
            return this;
        } catch (IOException var4) {
            log.error("Get template has error! The template name is : " + templateName, var4);
            throw new UtilException("Get template has error! The template name is : " + templateName, var4);
        }
    }

    public void generate(Object model, OutputStream outputStream) {
        try (BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(outputStream, StandardCharsets.UTF_8), 10240);) {
            Template current = this.currentTemplate.get();
            if (current == null) {
                throw new UtilException("Current Template is null!");
            } else {
                current.process(model, writer);
                writer.flush();
            }
        } catch (Exception e) {
            throw new UtilException("Create a buffered writer exception!");
        } finally {
            this.currentTemplate.remove();
        }
    }

}
