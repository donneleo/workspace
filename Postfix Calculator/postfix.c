#include <stdio.h>
#include <stdlib.h>
#include <assert.h>
#include <math.h>
#include <string.h>

double evaluate_postfix_expression(char ** expr, int nterms){
  double newValue;
  struct double_stack * stack1 = double_stack_new(nterms);
  int i;
  for(i = 0; i < nterms; i++){
	  if((expr[i][0] <= '9' && expr[i][0] >= '0') || (expr[i][0] == '-' && expr[i][1] <= '9' && expr[i][1] >= '0')){
	  double_stack_push(stack1, atof(expr[i]));
	  }
	  else{
	  int op;
	  if(expr[i][0] == '+') op = 1;
	  else if(expr[i][0] == '-') op = 2;
	  else if(expr[i][0] == 'X') op = 3;
	  else if(expr[i][0] == '/') op = 4;
	  else if(expr[i][0] == '^') op = 5;
	  
	  
	  switch(op){
	  
		  double value1; 
		  double value2;
		  double op_to_pop;
		  
		  case 1:
			  value1 = double_stack_pop(stack1);
		          value2 = double_stack_pop(stack1);
		          //op_to_pop = double_stack_pop(stack1);
			  newValue = value1+value2;
			  double_stack_push(stack1, newValue);
			  break;
		  
		  case 2:
			  value1 = double_stack_pop(stack1);
		          value2 = double_stack_pop(stack1);
		          //op_to_pop = double_stack_pop(stack1);
			  newValue = value2 - value1;
			  double_stack_push(stack1, newValue);
			  break;
		  
		  case 3:
			  value1 = double_stack_pop(stack1);
		          value2 = double_stack_pop(stack1);
			  //op_to_pop = double_stack_pop(stack1);
			  newValue = value1 * value2;
			  double_stack_push(stack1, newValue);
			  break;
		  
		  case 4:
			  value1 = double_stack_pop(stack1);
		          value2 = double_stack_pop(stack1);
		          //op_to_pop = double_stack_pop(stack1);
			  newValue = value2 / value1;
			  double_stack_push(stack1, newValue);
			  break;
		  
		  case 5:
			  value1 = double_stack_pop(stack1);
		          value2 = double_stack_pop(stack1);
		          //op_to_pop = double_stack_pop(stack1);
			  newValue = pow(value2, value1);
			  double_stack_push(stack1, newValue);
			  break;
		  
		  }
	  
	  }
	  
  }
  return newValue;
   
}
