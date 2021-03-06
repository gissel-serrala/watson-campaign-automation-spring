package com.github.ka4ok85.wca.options;

import com.github.ka4ok85.wca.constants.Visibility;
import org.apache.commons.lang3.time.DateUtils;

import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.Date;

public class ScheduleMailingOptions extends AbstractOptions {

    public enum SendType {
        TEXT, HTML
    }

    private Visibility visibility;
    private String scheduled;
    private Long templateId;
    private Long listId;
    private String mailingName;
    private SendType sendType;
    private String parentFolderPath;
    private int scheduleInMinutes = 2;

    public ScheduleMailingOptions(Long templateId, Long listId, String mailingName){
        this.templateId = templateId;
        this.listId = listId;
        this.mailingName = mailingName;
        this.visibility = Visibility.SHARED;
        this.scheduled = getCurrentTimeStampPlusMinutes();
    }

    public String getCurrentTimeStampPlusMinutes() {
        LocalDateTime someMinutesLater = LocalDateTime.now().plusMinutes(scheduleInMinutes);
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MM/dd/yyyy hh:mm:ss a");
        return someMinutesLater.format(formatter);
    }

    public Visibility getVisibility() {
        return visibility;
    }

    public void setVisibility(Visibility visibility) {
        this.visibility = visibility;
    }

    public Long getTemplateId() {
        return templateId;
    }

    public void setTemplateId(Long templateId) {
        if (templateId < 1L) {
            throw new RuntimeException("Template ID must be greater than zero. Provided Template ID = " + templateId);
        } else {
            this.templateId = templateId;
        }
    }

    public Long getListId() {
        return listId;
    }

    public void setListId(Long listId) {
        if (listId < 1L) {
            throw new RuntimeException("List ID must be greater than zero. Provided List ID = " + listId);
        } else {
            this.listId = listId;
        }
    }

    public String getMailingName() {
        return mailingName;
    }

    public void setMailingName(String mailingName) {
        this.mailingName = mailingName;
    }

    public SendType getSendType() {
        return sendType;
    }

    public void setSendType(SendType sendType) {
        this.sendType = sendType;
    }

    public String getParentFolderPath() {
        return parentFolderPath;
    }

    public void setParentFolderPath(String parentFolderPath) {
        this.parentFolderPath = parentFolderPath;
    }

    public String getScheduled() {
        return scheduled;
    }

    public void setScheduledByDateTime(String scheduled) {
        try {
            if(checkDateTimeOfCorrectFormat(scheduled))
                this.scheduled = scheduled;
        } catch (DateTimeParseException e) {
            e.printStackTrace();
        }
    }

    public static boolean checkDateTimeOfCorrectFormat(String dateTime) throws DateTimeParseException {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MM/dd/yyyy hh:mm:ss a");
        return dateTime.equals(LocalDateTime.parse(dateTime, formatter).toString());
    }

    public int getScheduledInMinutes() {
        return scheduleInMinutes;
    }

    public void setScheduledInMinutes(int scheduleInMinutes) {
        this.scheduleInMinutes = scheduleInMinutes;
        this.scheduled = getCurrentTimeStampPlusMinutes();
    }
}
