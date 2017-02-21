package com.power.entity;

public class RingBuffer {

    public Long[] elements = null;

    private int capacity = 0;
    private int writePos = 0;
    private int available = 0;

    public RingBuffer(int capacity) {
        this.capacity = capacity;
        this.elements = new Long[capacity];
    }

    public void reset() {
        this.writePos = 0;
        this.available = 0;
    }

    public int capacity() {
        return this.capacity;
    }

    public int available() {
        return this.available;
    }

    public int remainingCapacity() {
        return this.capacity - this.available;
    }

    public void put(Long element) {
        if (writePos >= capacity) {
            writePos = 0;
        }
        elements[writePos] = element;
        writePos++;
        available++;
    }

    public Long takeTail() {
        if (available == 0) {
            return null;
        }
        int nextSlot = writePos - available;
        if (nextSlot < 0) {
            nextSlot += capacity;
        }
        return elements[nextSlot];
    }

    public void deleteTail() {
        int nextSlot = writePos - available;
        if (nextSlot < 0) {
            nextSlot += capacity;
        }
        elements[nextSlot] = null;
        available--;
    }
}
