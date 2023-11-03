public class MyHashMap<K, V> {
    private static final int DEFAULT_SIZE = 8;
    private Entry<K, V>[] entries = new Entry[DEFAULT_SIZE];
    private int size;


    public boolean containsKey(K key) {
        int index = getBucketIndex(key);
        Entry<K, V> current = entries[index];
        if (current == null) {
            return false;
        }
        if (current.key.equals(key)) {
            return true;
        } else {
            while (current.next != null) {
                current = current.next;
                if (current.key.equals(key)) {
                    return true;
                }
            }
            return false;
        }
    }


    public void put(K key, V value) {
        int index = getBucketIndex(key);
        Entry<K, V> entry = new Entry<>(key, value, null);

        if (entries[index] == null) {
            entries[index] = entry;
        } else {
            Entry<K, V> lastInBucket = entries[index];
            if (lastInBucket.key.equals(key)) {
                lastInBucket.value = value;
                return;
            }
            while (lastInBucket.next != null) {
                lastInBucket = lastInBucket.next;
                if (lastInBucket.key.equals(key)) {
                    lastInBucket.value = value;
                    return;
                }
            }
            lastInBucket.next = entry;
        }
        size++;
    }

    public void remove(K key) {
        int index = getBucketIndex(key);
        if (containsKey(key)) {
            Entry<K, V> current = entries[index];
            if (current.next == null) {
                current.key = null;
                current.value = null;
                entries[index] = null;
                size--;
            } else {
                if (current.key.equals(key)) {
                    entries[index] = current.next;
                    current.value = null;
                    current.key = null;
                    current.next = null;
                    size--;
                } else {
                    Entry<K, V> previous = current;
                    current = current.next;
                    while (current.next != null) {
                        if (current.key.equals(key)) {
                            previous.next = current.next;
                            current.value = null;
                            current.key = null;
                            current.next = null;
                            size--;
                            return;
                        }
                        current = current.next;
                    }
                }
            }
        }
    }

    public void clear() {
        for (int i = 0; i < entries.length; i++) {
            if (entries[i] != null) {
                if (entries[i].next != null) {
                    Entry<K, V> current = entries[i];
                    Entry<K, V> next;
                    while (current.next != null) {
                        next = current.next;
                        current.key = null;
                        current.value = null;
                        current.next = null;
                        current = next;
                    }
                }
                entries[i] = null;
            }
        }
        size = 0;
    }

    public int size() {
        return size;
    }

    public V get(K key) {
        int index = getBucketIndex(key);
        if (entries[index] == null) {
            return null;
        }

        Entry<K, V> current = entries[index];

        if (current.key.equals(key)) {
            return current.value;
        } else {
            while (current.next != null) {
                current = current.next;
                if (current.key.equals(key)) {
                    return current.value;
                }
            }
            return null;
        }
    }

    private int getBucketIndex(K key) {
        return Math.abs(key.hashCode()) % entries.length;
    }

    private static class Entry<K, V> {
        private K key;
        private V value;
        private Entry<K, V> next;

        public Entry(K key, V value, Entry<K, V> next) {
            this.key = key;
            this.value = value;
            this.next = next;
        }
    }
}


class MyHashMapTest {
    public static void main(String[] args) {
        MyHashMap<String, String> myHashMap = new MyHashMap<>();

        // -- Size of HashMap --
        System.out.println("HashMap size is " + myHashMap.size());
        // -- end --

        // -- Get element that is not in HashMap --
        System.out.println("Value is '" + myHashMap.get("Sss") + "'");
        // -- end --

        // -- Add new 19 elements --
        myHashMap.put("Aaa", "Aaa value");
        myHashMap.put("Bbb", "Bbb value");
        myHashMap.put("Ccc", "Ccc value");
        myHashMap.put("Ddd", "Ddd value");
        myHashMap.put("Eee", "Eee value");
        myHashMap.put("Fff", "Fff value");
        myHashMap.put("Ggg", "Ggg value");
        myHashMap.put("Hhh", "Hhh value");
        myHashMap.put("Iii", "Iii value");
        myHashMap.put("Jjj", "Jjj value");
        myHashMap.put("Kkk", "Kkk value");
        myHashMap.put("Lll", "Lll value");
        myHashMap.put("Mmm", "Mmm value");
        myHashMap.put("Nnn", "Nnn value");
        myHashMap.put("Ooo", "Ooo value");
        myHashMap.put("Ppp", "Ppp value");
        myHashMap.put("Qqq", "Qqq value");
        myHashMap.put("Rrr", "Rrr value");
        myHashMap.put("Sss", "Sss value");
        // -- end --

        // -- Size of HashMap --
        System.out.println("HashMap size is " + myHashMap.size());
        // -- end --

        //  -- Get elements from HashMap with their bucket index (All buckets are filling) --
        System.out.println("Value is '" + myHashMap.get("Aaa") + "'. Bucket is " + ("Aaa".hashCode() % 8));
        System.out.println("Value is '" + myHashMap.get("Bbb") + "'. Bucket is " + ("Bbb".hashCode() % 8));
        System.out.println("Value is '" + myHashMap.get("Ccc") + "'. Bucket is " + ("Ccc".hashCode() % 8));
        System.out.println("Value is '" + myHashMap.get("Ddd") + "'. Bucket is " + ("Ddd".hashCode() % 8));
        System.out.println("Value is '" + myHashMap.get("Eee") + "'. Bucket is " + ("Eee".hashCode() % 8));
        System.out.println("Value is '" + myHashMap.get("Fff") + "'. Bucket is " + ("Fff".hashCode() % 8));
        System.out.println("Value is '" + myHashMap.get("Ggg") + "'. Bucket is " + ("Ggg".hashCode() % 8));
        System.out.println("Value is '" + myHashMap.get("Hhh") + "'. Bucket is " + ("Hhh".hashCode() % 8));
        System.out.println("Value is '" + myHashMap.get("Iii") + "'. Bucket is " + ("Iii".hashCode() % 8));
        System.out.println("Value is '" + myHashMap.get("Jjj") + "'. Bucket is " + ("Jjj".hashCode() % 8));
        System.out.println("Value is '" + myHashMap.get("Kkk") + "'. Bucket is " + ("Kkk".hashCode() % 8));
        System.out.println("Value is '" + myHashMap.get("Lll") + "'. Bucket is " + ("Lll".hashCode() % 8));
        System.out.println("Value is '" + myHashMap.get("Mmm") + "'. Bucket is " + ("Mmm".hashCode() % 8));
        System.out.println("Value is '" + myHashMap.get("Nnn") + "'. Bucket is " + ("Nnn".hashCode() % 8));
        System.out.println("Value is '" + myHashMap.get("Ooo") + "'. Bucket is " + ("Ooo".hashCode() % 8));
        System.out.println("Value is '" + myHashMap.get("Ppp") + "'. Bucket is " + ("Ppp".hashCode() % 8));
        System.out.println("Value is '" + myHashMap.get("Qqq") + "'. Bucket is " + ("Qqq".hashCode() % 8));
        System.out.println("Value is '" + myHashMap.get("Rrr") + "'. Bucket is " + ("Rrr".hashCode() % 8));
        System.out.println("Value is '" + myHashMap.get("Sss") + "'. Bucket is " + ("Sss".hashCode() % 8));
        // -- end --

        // -- Get element that is not in HashMap --
        System.out.println("Value is '" + myHashMap.get("1111111") + "'");
        // -- end --

        // -- Test method 'containsKey' which using in others method --
        System.out.println("Contains 'Ss' - " + myHashMap.containsKey("Ss"));
        System.out.println("Contains 'Sss' - " + myHashMap.containsKey("Sss"));
        // -- end --

        // -- Put the same key 'Sss' with another value --
        myHashMap.put("Sss", "Sss new value");
        System.out.println("Value is '" + myHashMap.get("Sss") + "'. Bucket is " + ("Sss".hashCode() % 8));
        System.out.println("HashMap size is " + myHashMap.size());
        // -- end --

        // -- Test clear method --
//        myHashMap.clear();
//        System.out.println("HashMap size after clear " + myHashMap.size());
//        System.out.println("Value is " + myHashMap.get("Sss"));
        // -- end --

        // -- Test remove method --
        System.out.println(" --- Remove element 'Hhh' ---");
        myHashMap.remove("Hhh");
        System.out.println("Get value of element(Hhh) after remove - " + myHashMap.get("Hhh"));
        System.out.println("HashMap size after remove " + myHashMap.size());
        System.out.println(" --- Remove element 'Iii' ---");
        myHashMap.remove("Iii");
        System.out.println("Get element(Iii) after remove - " + myHashMap.get("Iii"));
        System.out.println("HashMap size after remove " + myHashMap.size());

    }

}