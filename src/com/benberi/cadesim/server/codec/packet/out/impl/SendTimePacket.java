package com.benberi.cadesim.server.codec.packet.out.impl;

import com.benberi.cadesim.server.codec.OutGoingPackets;
import com.benberi.cadesim.server.codec.util.PacketLength;
import com.benberi.cadesim.server.codec.packet.out.OutgoingPacket;

/**
 * Sends the blockade time and current turn time
 */
public class SendTimePacket extends OutgoingPacket {

    private int gameTime;
    private int turnTime;

    public SendTimePacket() {
        super(OutGoingPackets.TIME_PACKET);
    }

    public void setGameTime(int gameTime) {
        this.gameTime = gameTime;
    }

    public void setTurnTime(int turnTime) {
        this.turnTime = turnTime;
        if (turnTime < 0) {
            this.turnTime = 0;
        }
    }

    @Override
    public void encode() {
        // The blockade time
        int blockadeTime = gameTime;

        // The turn time
        int turnTime = this.turnTime;

        setPacketLengthType(PacketLength.BYTE);
        writeInt(blockadeTime); // blockade time
        writeInt(turnTime); // turn time
        setLength(getBuffer().readableBytes()); // 2 integers 8 bits
    }
}
