// create a new empty stack
struct double_stack * double_stack_new(int max_size) {
  struct double_stack * result;

  // allocate space for the stack header
  result = malloc(sizeof(struct double_stack));
  result->max_size = max_size;
  result->top = 0;
  // allocate space for the data stored in the stack
  result->items = malloc(sizeof(double)*max_size);
  // return a pointer to the newly-allocated stack
  return result;
}

/*// create a new empty character stack
struct char_stack * char_stack_new(char max_size) {
  struct char_stack * result;

  // allocate space for the stack header
  result = malloc(sizeof(struct double_stack));
  result->max_size = max_size;
  result->top = 0;
  // allocate space for the data stored in the stack
  result->items = malloc(sizeof(char)*max_size);
  // return a pointer to the newly-allocated stack
  return result;
}*/


// push a value onto the stack
void double_stack_push(struct double_stack * this, double value)
{
     this->items[this->top] = value;
     this->top++;

}

/*//push a character onto the character stack
void char_stack_push(struct char_stack * this, char n)
{
   if(this->max_size == this->top)
   {
     //printf("Stack is full. Could not push %f \n", value);
     return;
   }
   else
   {
     this->items[this->top] = n;
     printf("%f was pushed", this->items[this->top]);
     this->top++;
    // printf("Value successfully pushed onto the stack  \n");
     return;
   }

}*/

// pop a value from the stack
double double_stack_pop(struct double_stack * this)
{
    if(this->top == 0)
    {
     // printf("The stack is empty and there was nothing to pop  \n");
      return 0.0;
    }
    else
    {
      this->top--;
      double poppedValue = this->items[this->top];
      //printf( "%f was popped from the stack \n", poppedValue);
      return poppedValue;
    }

}
