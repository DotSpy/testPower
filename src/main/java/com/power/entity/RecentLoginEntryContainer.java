package com.power.entity;

import java.util.HashMap;
import java.util.Map;

public class RecentLoginEntryContainer {

    public final static RecentLoginEntryContainer INSTANCE = new RecentLoginEntryContainer();

    private RecentLoginEntryContainer() {
    }

    // лишняя параметризация справа
    private Map<String, RingBuffer> recentLoginMap = new HashMap<String, RingBuffer>();

    // пересмотри этот метод, он за исключением создания буффера идентичен
    //
    public Boolean addLogin(LogEnry logEnry) {
        if (recentLoginMap.containsKey(logEnry.getIp())) {
            recentLoginMap.get(logEnry.getIp()).put(logEnry.getEpochTime());
            if (recentLoginMap.get(logEnry.getIp()).available() == 5) {
                return true;
            }
        } else {
            // если мы для айпи создаем новый буффер, то он скорее всего не будет содержать 5
            // элементов, + можно заюзать computeIfAbsent метод
            // опять же, у тебя 5-ка магическое число, непорядок
            RingBuffer ringBuffer = new RingBuffer(5);
            ringBuffer.put(logEnry.getEpochTime());
            recentLoginMap.put(logEnry.getIp(), ringBuffer);
            if (recentLoginMap.get(logEnry.getIp()).available() == 5) {
                return true;
            }
        }
        return false;
    }

    public Map<String, RingBuffer> getRecentLoginMap() {
        return recentLoginMap;
    }
}
