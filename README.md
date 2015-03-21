# bf-mips
An overengineered Brainfuck to MIPS compiler. Really the only data structure you need to compile Brainfuck is a stack.


## Example:
```
String code = "++++++++[>++++[>++>+++>+++>+<<<<-]>+>+>->>+[<]<-]>>.>---.+++++++..+++.>>.<-.<.+++.------.--------.>>+.>++.";
char[] tokens = BFLexer.lex(code);
AST tree = BFParser.parse(tokens);
CodeGenerator cGen = new CodeGenerator(tree);
System.out.println(cGen.generate());
````

outputs

```
.data
array: .word 0:30000
.text

MAIN:
	la $t1, array
	jal INCVAL
	jal INCVAL
	jal INCVAL
	jal INCVAL
	jal INCVAL
	jal INCVAL
	jal INCVAL
	jal INCVAL
LOOP_1:
	lw $a0, 0($t1)
	bgtz $a0, BEGIN_LOOP_1
	j END_LOOP_1
BEGIN_LOOP_1:
	jal INCPT
	jal INCVAL
	jal INCVAL
	jal INCVAL
	jal INCVAL
LOOP_2:
	lw $a0, 0($t1)
	bgtz $a0, BEGIN_LOOP_2
	j END_LOOP_2
BEGIN_LOOP_2:
	jal INCPT
	jal INCVAL
	jal INCVAL
	jal INCPT
	jal INCVAL
	jal INCVAL
	jal INCVAL
	jal INCPT
	jal INCVAL
	jal INCVAL
	jal INCVAL
	jal INCPT
	jal INCVAL
	jal DECPT
	jal DECPT
	jal DECPT
	jal DECPT
	jal DECVAL
	j LOOP_2
END_LOOP_2:
	jal INCPT
	jal INCVAL
	jal INCPT
	jal INCVAL
	jal INCPT
	jal DECVAL
	jal INCPT
	jal INCPT
	jal INCVAL
LOOP_3:
	lw $a0, 0($t1)
	bgtz $a0, BEGIN_LOOP_3
	j END_LOOP_3
BEGIN_LOOP_3:
	jal DECPT
	j LOOP_3
END_LOOP_3:
	jal DECPT
	jal DECVAL
	j LOOP_1
END_LOOP_1:
	jal INCPT
	jal INCPT
	jal OUTPUT
	jal INCPT
	jal DECVAL
	jal DECVAL
	jal DECVAL
	jal OUTPUT
	jal INCVAL
	jal INCVAL
	jal INCVAL
	jal INCVAL
	jal INCVAL
	jal INCVAL
	jal INCVAL
	jal OUTPUT
	jal OUTPUT
	jal INCVAL
	jal INCVAL
	jal INCVAL
	jal OUTPUT
	jal INCPT
	jal INCPT
	jal OUTPUT
	jal DECPT
	jal DECVAL
	jal OUTPUT
	jal DECPT
	jal OUTPUT
	jal INCVAL
	jal INCVAL
	jal INCVAL
	jal OUTPUT
	jal DECVAL
	jal DECVAL
	jal DECVAL
	jal DECVAL
	jal DECVAL
	jal DECVAL
	jal OUTPUT
	jal DECVAL
	jal DECVAL
	jal DECVAL
	jal DECVAL
	jal DECVAL
	jal DECVAL
	jal DECVAL
	jal DECVAL
	jal OUTPUT
	jal INCPT
	jal INCPT
	jal INCVAL
	jal OUTPUT
	jal INCPT
	jal INCVAL
	jal INCVAL
	jal OUTPUT
	li $v0, 10
	syscall

INCPT:
	addiu $t1, $t1, 4
	jr $ra
DECPT:
	addiu $t1, $t1, -4
	jr $ra
INCVAL:
	lw $a0, 0($t1)
	addi $a0, $a0, 1
	sw $a0, 0($t1)
	jr $ra
DECVAL:
	lw $a0, 0($t1)
	addi $a0, $a0, -1
	sw $a0, 0($t1)
	jr $ra
INPUT:
	li $v0, 5
	syscall
	sw $v0, 0($t1)
	jr $ra
OUTPUT:
	lw $a0, 0($t1)
	li $v0, 1
	syscall
	jr $ra
```

Which is arguably more readable than the original Brainfuck.
