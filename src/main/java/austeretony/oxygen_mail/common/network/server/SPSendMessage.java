package austeretony.oxygen_mail.common.network.server;

import austeretony.oxygen.common.network.ProxyPacket;
import austeretony.oxygen.util.PacketBufferUtils;
import austeretony.oxygen_mail.common.MailManagerServer;
import austeretony.oxygen_mail.common.main.Mail;
import net.minecraft.network.INetHandler;
import net.minecraft.network.PacketBuffer;

public class SPSendMessage extends ProxyPacket {

    private String addressee;

    private Mail message;

    public SPSendMessage() {}

    public SPSendMessage(String addressee, Mail message) {
        this.addressee = addressee;
        this.message = message;
    }

    @Override
    public void write(PacketBuffer buffer, INetHandler netHandler) {
        PacketBufferUtils.writeString(this.addressee, buffer);
        this.message.write(buffer);
    }

    @Override
    public void read(PacketBuffer buffer, INetHandler netHandler) {
        MailManagerServer.instance().sendMail(getEntityPlayerMP(netHandler), PacketBufferUtils.readString(buffer), Mail.read(buffer));
    }
}