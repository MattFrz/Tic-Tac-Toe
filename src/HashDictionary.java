/*
Author: Matt Farzaneh
Student number: 251370889

Description:

The HashDictionary class provides a hash table to store the configurations efficiently.
all entries in the hash table are stored in a linked list to handle collisions via "chaining".
Each element is a data object containing a game configuration and its score.
*/
import  java.util.LinkedList;

public class HashDictionary implements DictionaryADT{
	
    // Define the size of the hash table
    private LinkedList<Data>[] hashTable;
    private int size;
    private int numRecords;

    // Constructor
    @SuppressWarnings("unchecked")
    public HashDictionary(int size) {
        this.size = size;
        this.numRecords = 0;
     // Create the array of LinkedLists
        this.hashTable = new LinkedList[size];
        for (int i = 0; i < size; i++) {
        	// Initialize each index with an empty LinkedList
            hashTable[i] = new LinkedList<>();
        }
    }

	// Polynomial hash function
	private int hashFunction(String key) {
		int hash = 0;
        int p = 101;
        int len = key.length();

        for (int i = 0; i < len; i++) {
            hash = (hash * p + key.charAt(i)) % size;
        }

        return Math.abs(hash) % size;
	}
	
	// Puts game configuration in hash table
	public int put(Data record) throws DictionaryException {
	    // Calculate the hash index for the record's configuration
	    int index = hashFunction(record.getConfiguration());

	    // Check if there's already a linked list at this index
	    LinkedList<Data> bucket = hashTable[index];
	    boolean isCollision = false;

	    // If the hashTable already exists handle it
	    if (bucket == null) {
	        bucket = new LinkedList<>();
	        hashTable[index] = bucket;
	    }
	    else if (!bucket.isEmpty()) {
	        isCollision = true; // There's already at least one item (collision occurs)
	    }
	    
	    for (Data data : bucket) {
	        if (data.getConfiguration().equals(record.getConfiguration())) {
	            throw new DictionaryException();
	        }
	    }

	    // Add the new record to the linked list and increment numRecords
	    bucket.add(record);
	    numRecords++;
	    
	    // Return 1 if there was a collision, otherwise return 0
	    if (isCollision) {
	        return 1;
	    } else {
	        return 0;
	    }
	}

	// Removes game configuration
	public void remove(String config) throws DictionaryException{
		int index = hashFunction(config);
		LinkedList<Data> bucket = hashTable[index];
		// Check if bucket is null or empty
		if(bucket == null || bucket.isEmpty()){
			throw new DictionaryException();
		}
		// If the key is the same as entered remove
		for(Data data : bucket) {
			if(data.getConfiguration().equals(config)){
				bucket.remove(data);
				numRecords--;
				return;
			}
		}
		throw new DictionaryException();
	}
	
	// Gets score associated with configuration
	public int get(String config) throws DictionaryException{
		int index = hashFunction(config);
		
		LinkedList<Data> bucket = hashTable[index];
		
		if(bucket != null && !bucket.isEmpty()) {
			
			for(Data data : bucket) {
				if(data.getConfiguration().equals(config)){
					return data.getScore();
				}
			}
		}
		// If the configuration is not found, return -1
		return -1;
	}
	
	// Returns the total number of record
	public int numRecords() {
		return numRecords;
	}
	
	
}
