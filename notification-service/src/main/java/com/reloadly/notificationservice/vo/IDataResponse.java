package com.reloadly.notificationservice.vo;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Data
@NoArgsConstructor
public class IDataResponse<M> implements DataResponse<M> {

    private boolean valid;

    private List<M> data;

    private List<Message> messages;

    private List<M> responseData;


    public IDataResponse(boolean valid) {
        this.valid = valid;
    }


    public List<M> getData() {
        return data;
    }


    public void setData(List<M> data) {
        this.data = data;
    }


    public final void addMessage(Message msg) {
        if (msg != null) {
            if (this.messages == null) {
                this.messages = new ArrayList();
            }
            this.messages.add(msg);
        }
    }

    public final void addMessage(String msg, String detail, Message.Severity severity) {
        if (msg != null || detail != null) {
            addMessage(new GlobalMessage(msg, detail, severity));
        }
    }


    public void addData(M dataEntry) {
        if (dataEntry != null) {
            if (this.data == null) {
                this.data = new ArrayList();
            }
            this.data.add(dataEntry);
        }
    }


    public boolean hasMessages() {
        return this.messages != null && !this.messages.isEmpty();
    }
}
