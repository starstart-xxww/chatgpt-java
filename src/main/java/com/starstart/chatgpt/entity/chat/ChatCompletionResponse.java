package com.starstart.chatgpt.entity.chat;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.starstart.chatgpt.entity.billing.Usage;
import lombok.Data;
import org.springframework.util.CollectionUtils;

import java.util.List;
import java.util.Optional;

/**
 * chat答案类
 *
 * @author plexpt
 */
@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class ChatCompletionResponse {
    private String id;
    private String object;
    private Long created;
    private String model;
    @JsonProperty("system_fingerprint")
    private String systemFingerprint;
    private List<ChatChoice> choices;
    private Usage usage;


    @JsonIgnoreProperties(ignoreUnknown = true)
    Object logprobs;


    public String toPlainString() {
        if (CollectionUtils.isEmpty(this.getChoices())) {
            return "";
        }


        return Optional.ofNullable(this.getChoices())
                .map(e -> e.get(0))
                .map(ChatChoice::getMessage)
                .map(Message::getContentStr)
                .orElse("");
    }

    public String toPlainStringStream() {
        if (CollectionUtils.isEmpty(this.getChoices())) {
            return "";
        }


        return Optional.ofNullable(this.getChoices())
                .map(e -> e.get(0))
                .map(ChatChoice::getDelta)
                .map(Message::getContentStr)
                .orElse("");
    }

}
