package org.telegram.telegrambots.meta.api.methods.send;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import lombok.experimental.SuperBuilder;
import lombok.experimental.Tolerate;
import lombok.extern.jackson.Jacksonized;
import org.telegram.telegrambots.meta.api.objects.InputFile;
import org.telegram.telegrambots.meta.api.objects.ReplyParameters;
import org.telegram.telegrambots.meta.api.objects.message.Message;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.ReplyKeyboard;
import org.telegram.telegrambots.meta.exceptions.TelegramApiRequestException;
import org.telegram.telegrambots.meta.exceptions.TelegramApiValidationException;
import org.telegram.telegrambots.meta.util.Validations;

/**
 * @author Ruben Bermudez
 * @version 1.0
 * Use this method to send static .WEBP, animated .TGS, or video .WEBM stickers.
 * On success, the sent Message is returned.
 */
@EqualsAndHashCode(callSuper = false)
@Getter
@Setter
@ToString
@RequiredArgsConstructor
@AllArgsConstructor
@SuperBuilder
@Jacksonized
@JsonIgnoreProperties(ignoreUnknown = true)
@JsonInclude(JsonInclude.Include.NON_NULL)
public class SendSticker extends SendMediaBotMethod<Message> {
    public static final String PATH = "sendsticker";

    public static final String STICKER_FIELD = "sticker";
    public static final String EMOJI_FIELD = "emoji";
    public static final String BUSINESS_CONNECTION_ID_FIELD = "business_connection_id";

    /**
     * Unique identifier for the chat to send the message to (Or username for channels)
     */
    @NonNull
    private String chatId;
    /**
     * Unique identifier for the target message thread (topic) of the forum;
     * for forum supergroups only
     */
    private Integer messageThreadId;
    /**
     * 	Sticker to send.
     * 	Pass a file_id as String to send a file that exists on the Telegram servers (recommended),
     * 	pass an HTTP URL as a String for Telegram to get a .WEBP sticker from the Internet, or upload a new
     * 	.WEBP, .TGS, or .WEBM sticker using multipart/form-data.
     * 	Video and animated stickers can't be sent via an HTTP URL.
     */
    @NonNull
    private InputFile sticker;
    /**
     * Optional.
     * Sends the message silently. Users will receive a notification with no sound.
     */
    private Boolean disableNotification;
    /**
     * Optional.
     * If the message is a reply, ID of the original message
     */
    private Integer replyToMessageId;
    /**
     * Optional.
     * Additional interface options.
     * A JSON-serialized object for an inline keyboard, custom reply keyboard, instructions to remove a reply keyboard
     * or to force a reply from the user.
     */
    private ReplyKeyboard replyMarkup;
    /**
     * Optional.
     * Pass True, if the message should be sent even if the specified replied-to message is not found
     */
    private Boolean allowSendingWithoutReply;
    /**
     * Optional.
     * Pass True, if the message should be sent even if the specified replied-to message is not found
     */
    private Boolean protectContent;
    /**
     * Optional
     * Emoji associated with the sticker; only for uploaded stickers
     */
    private String emoji;
    /**
     * Optional
     * Description of the message to reply to
     */
    private ReplyParameters replyParameters;
    /**
     * Optional.
     * Unique identifier of the business connection on behalf of which the message will be sent
     */
    private String businessConnectionId;
    /**
     * Optional
     * Unique identifier of the message effect to be added to the message
     */
    private String messageEffectId;
    /**
     * Optional
     * Pass True to allow up to 1000 messages per second, ignoring broadcasting limits for a fee of 0.1 Telegram Stars per message.
     * The relevant Stars will be withdrawn from the bot's balance
     */
    private Boolean allowPaidBroadcast;

    @Tolerate
    public void setChatId(@NonNull Long chatId) {
        this.chatId = chatId.toString();
    }

    public void enableNotification() {
        this.disableNotification = false;
    }

    public void disableNotification() {
        this.disableNotification = true;
    }

    @Override
    public Message deserializeResponse(String answer) throws TelegramApiRequestException {
        return deserializeResponse(answer, Message.class);
    }

    @Override
    public void validate() throws TelegramApiValidationException {
        Validations.requiredChatId(chatId, this);

        sticker.validate();

        if (replyMarkup != null) {
            replyMarkup.validate();
        }
        if (replyParameters != null) {
            replyParameters.validate();
        }
    }

    @Override
    public String getMethod() {
        return PATH;
    }

    @Override
    public InputFile getFile() {
        return sticker;
    }

    @Override
    public String getFileField() {
        return STICKER_FIELD;
    }


    public static abstract class SendStickerBuilder<C extends SendSticker, B extends SendStickerBuilder<C, B>> extends SendMediaBotMethodBuilder<Message, C, B> {
        @Tolerate
        public SendStickerBuilder<C, B> chatId(@NonNull Long chatId) {
            this.chatId = chatId.toString();
            return this;
        }
    }
}
