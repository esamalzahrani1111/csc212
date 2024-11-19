class HashMap {

    private Entry[] table;
    private int capacity;

    public HashMap(int capacity) {
        this.capacity = capacity;
        table = new Entry[capacity];
    }

    private int hash(int key) {
        return key % capacity;
    }

    public void put(int key, int value) {
        int index = hash(key);
        Entry current = table[index];

        while (current != null) {
            if (current.key == key) {
                current.value = value; // Update existing value
                return;
            }
            current = current.next;
        }

        // Insert new entry
        Entry newEntry = new Entry(key, value);
        newEntry.next = table[index];
        table[index] = newEntry;
    }

    public int get(int key) {
        int index = hash(key);
        Entry current = table[index];

        while (current != null) {
            if (current.key == key) {
                return current.value;
            }
            current = current.next;
        }

        return 0; // Default score if key is not found
    }

    public boolean containsKey(int key) {
        int index = hash(key);
        Entry current = table[index];

        while (current != null) {
            if (current.key == key) {
                return true;
            }
            current = current.next;
        }

        return false;
    }

    public int[] keys() {
        int[] keys = new int[capacity];
        int count = 0;
        for (int i = 0; i < capacity; i++) {
            Entry current = table[i];
            while (current != null) {
                keys[count++] = current.key;
                current = current.next;
            }
        }
        return keys;
    }

    public int[] values() {
        int[] values = new int[capacity];
        int count = 0;
        for (int i = 0; i < capacity; i++) {
            Entry current = table[i];
            while (current != null) {
                values[count++] = current.value;
                current = current.next;
            }
        }
        return values;
    }
}

class Entry {
    int key; // Document ID
    int value; // Score
    Entry next;

    Entry(int key, int value) {
        this.key = key;
        this.value = value;
        this.next = null;
    }
}