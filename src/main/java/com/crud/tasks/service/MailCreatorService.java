package com.crud.tasks.service;

import com.crud.tasks.config.AdminConfig;
import com.crud.tasks.config.CompanyConfig;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;

import java.util.ArrayList;
import java.util.List;

@Service
public class MailCreatorService {

    @Autowired
    @Qualifier("templateEngine")
    private TemplateEngine templateEngine;

    @Autowired
    private AdminConfig adminConfig;

    @Autowired
    private CompanyConfig companyConfig;

    @Autowired
    private DbService dbService;

    private Context getContext() {
        Context context = new Context();
        context.setVariable("tasks_url", "http://localhost:8888/");
        context.setVariable("button", "Visit website");
        context.setVariable("admin_name", adminConfig);
        context.setVariable("company_name", companyConfig.getCompanyName());
        context.setVariable("company_goal", companyConfig.getCompanyGoal());
        context.setVariable("company_email", companyConfig.getCompanyEmail());
        context.setVariable("company_phone", companyConfig.getCompanyPhone());
        return context;
    }

    public String buildTrelloCardEmail(String message) {
        List<String> functionality = new ArrayList<>();
        functionality.add("You can manage your tasks");
        functionality.add("Provides connection with Trello Account");
        functionality.add("Application allows sending tasks to Trello");

        Context context = getContext();
        context.setVariable("message", message);
        context.setVariable("goodbye_message", "Till the next time, " + adminConfig.getAdminName() + "!");
        context.setVariable("show_button", true);
        context.setVariable("is_friend", true);
        context.setVariable("application_functionality", functionality);
        return templateEngine.process("mail/created-trello-card-mail", context);
    }

    public String buildDailyInfoEmail(String message) {
        List<String> tasks = new ArrayList<>();
        dbService.getAllTasks().stream()
                .forEach(task -> tasks.add(task.getTitle()));

        Context context = getContext();
        context.setVariable("message", message);
        context.setVariable("goodbye_message", "Till tomorrow, " + adminConfig.getAdminName() + "!");
        context.setVariable("show_button", true);
        context.setVariable("is_friend", false);
        context.setVariable("tasks", tasks);
        return templateEngine.process("mail/daily-info-email", context);
    }

}