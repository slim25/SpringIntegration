package com.saniok.springintegration.service;

import com.saniok.springintegration.model.Message;

/**
 * Created by Oleksandr_Tertyshnyi on 1/6/2017.
 */
public interface MessageListener {

    public void processMessage(Message message);

}
