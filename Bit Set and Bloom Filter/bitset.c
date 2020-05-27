#include <math.h>

// create a new, empty bit vector set with a universe of 'size' items
struct bitset * bitset_new(int size) {

	struct bitset * bitVector = calloc(1, sizeof(struct bitset));						//create the bitset, use calloc to set everything to 0
 	 bitVector->size_in_words = ceil(size/64);								//have size_in_words == the max-rounded-up value of size/64(64 bits in word)
 	 bitVector->bits = calloc(bitVector->size_in_words, sizeof(uint64_t));					//have the bits in the bitset be the size of uint64_t)
 	 bitVector->universe_size = size;									//universe size of the bitset will be the inputted size
   

 	 return bitVector;
}

// get the size of the universe of items that could be stored in the set
int bitset_size(struct bitset * this) {

	return this->universe_size;
}

// get the number of items that are stored in the set
int bitset_cardinality(struct bitset * this){

	int index = 0;
  	 uint64_t characterMask = 1;
  	 int index2 = 0;
   
 	  for(int i= 0; i< bitset_size(this); i++)
  	 {
 	     if((this->bits[index2] & characterMask) == characterMask)                                  //check if the bit at the position is equal to 1 after AND-ing
            {
               index++;                        								//if so, increment index++
            }
              characterMask <<= 1;									//shift the 1 across by one bit, allowing the next bit to be checked against the mask
            if(characterMask == 0)									//check that the bit hasnt been pushed off the mask
            {
              index2++;											//if it has, increment index 2
              characterMask = 1;									//and reset the mask to 1
            }
          }
   
   return index;
}

// check to see if an item is in the set
int bitset_lookup(struct bitset * this, int item){

     uint64_t characterMask = 1;						//set a mask with 1 at the LSB
     characterMask <<= item%64;							//get the modulo of the space to check %64(because of uint64_t), and shift the mask to the right place
     int index = item/64;							//the index will be at the space/64 becuase that's how the bitset_vector was set up
   
     if((this->bits[index] & characterMask) == characterMask)			//AND the bit at the index with the mask, and then check it against the mask
     {
       return 1;								//if they are equal, return 1; indicating that the bit was in the set
     }
     {
       return 0;								//if not, return 0
     }
}

// add an item, with number 'item' to the set
// has no effect if the item is already in the set
void bitset_add(struct bitset * this, int item) {

     uint64_t characterMask = 1;						//set up a mask
     characterMask <<= item % 64;						//shift it to the correct position using the position % 64
     int index = item / 64;							//get the index for the bit using the position / 64
     this->bits[index] |= characterMask;					//OR the bit with the Mask to set the bit to what it needs to be, becuase 1 OR 0 == 1
}

// remove an item with number 'item' from the set
void bitset_remove(struct bitset * this, int item) {

   uint64_t characterMask = 1;							//set up a mask
   int index = item/64;								//get the index for the bit to remove
   
   characterMask <<= item%64;							//shift the mask to where it needs to be
   
   characterMask = ~characterMask;			//NOT the mask, so that every bit == 1(thus not effecting any other bits in the bitset), exect the bit that you need to mask bit
   
   this->bits[index] &= characterMask;						//use the index to get the bit to check and AND it with the mask, because 1 AND 0 == 0, thud deleting the bit
}

// place the union of src1 and src2 into dest;
// all of src1, src2, and dest must have the same size universe
void bitset_union(struct bitset * dest, struct bitset * src1,
    struct bitset * src2) {
    
       for(int i = 0; i < dest->size_in_words; i++)					//have the loop go through every bit in the destination bitset
       {
          dest->bits[i] = src1->bits[i] | src2->bits[i];		//have every bit in the destination bitset be the result of OR-ing the relative bits from the two bitsets
       }  
}

// place the intersection of src1 and src2 into dest
// all of src1, src2, and dest must have the same size universe
void bitset_intersect(struct bitset * dest, struct bitset * src1,
    struct bitset * src2) {
    
     for(int i = 0; i < dest->size_in_words; i++)                              //Same as the last method, but use AND instead of OR
     {
          dest->bits[i] = src1->bits[i] & src2->bits[i];
     }
     
}     
