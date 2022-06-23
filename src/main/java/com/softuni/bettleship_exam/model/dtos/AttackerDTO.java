package com.softuni.bettleship_exam.model.dtos;

public class AttackerDTO {

    private long attackerShipId;

    private long defenderShipId;

    public long getAttackerShipId() {
        return attackerShipId;
    }

    public AttackerDTO setAttackerShipId(long attackerShipId) {
        this.attackerShipId = attackerShipId;
        return this;
    }

    public long getDefenderShipId() {
        return defenderShipId;
    }

    public AttackerDTO setDefenderShipId(long defenderShipId) {
        this.defenderShipId = defenderShipId;
        return this;
    }

    @Override
    public String toString() {
        return "AttackerDTO{" +
                "attackerShipId=" + attackerShipId +
                ", defenderShipId=" + defenderShipId +
                '}';
    }
}
