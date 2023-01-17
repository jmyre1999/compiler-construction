	.file	"runtime.c"
	.section .rdata,"dr"
.LC0:
	.ascii "%d\12\0"
	.text
	.globl	println
	.def	println;	.scl	2;	.type	32;	.endef
	.seh_proc	println
println:
	pushq	%rbp
	.seh_pushreg	%rbp
	movq	%rsp, %rbp
	.seh_setframe	%rbp, 0
	subq	$32, %rsp
	.seh_stackalloc	32
	.seh_endprologue
	movl	%ecx, 16(%rbp)
	movl	16(%rbp), %edx
	leaq	.LC0(%rip), %rcx
	call	printf
	nop
	addq	$32, %rsp
	popq	%rbp
	ret
	.seh_endproc
	.globl	genIntArray
	.def	genIntArray;	.scl	2;	.type	32;	.endef
	.seh_proc	genIntArray
genIntArray:
	pushq	%rbp
	.seh_pushreg	%rbp
	movq	%rsp, %rbp
	.seh_setframe	%rbp, 0
	subq	$48, %rsp
	.seh_stackalloc	48
	.seh_endprologue
	movl	%ecx, 16(%rbp)
	movl	16(%rbp), %eax
	cltq
	salq	$2, %rax
	movq	%rax, %rcx
	call	malloc
	movq	%rax, -16(%rbp)
	movl	$0, -4(%rbp)
	jmp	.L3
.L4:
	movl	-4(%rbp), %eax
	cltq
	leaq	0(,%rax,4), %rdx
	movq	-16(%rbp), %rax
	addq	%rdx, %rax
	movl	$0, (%rax)
	addl	$1, -4(%rbp)
.L3:
	movl	-4(%rbp), %eax
	cmpl	16(%rbp), %eax
	jl	.L4
	movq	-16(%rbp), %rax
	addq	$48, %rsp
	popq	%rbp
	ret
	.seh_endproc
	.globl	setArrayIndex
	.def	setArrayIndex;	.scl	2;	.type	32;	.endef
	.seh_proc	setArrayIndex
setArrayIndex:
	pushq	%rbp
	.seh_pushreg	%rbp
	movq	%rsp, %rbp
	.seh_setframe	%rbp, 0
	.seh_endprologue
	movq	%rcx, 16(%rbp)
	movl	%edx, 24(%rbp)
	movl	%r8d, 32(%rbp)
	cmpl	$9, 24(%rbp)
	jg	.L8
	cmpl	$0, 24(%rbp)
	js	.L8
	movl	24(%rbp), %eax
	cltq
	leaq	0(,%rax,4), %rdx
	movq	16(%rbp), %rax
	addq	%rax, %rdx
	movl	32(%rbp), %eax
	movl	%eax, (%rdx)
.L8:
	nop
	popq	%rbp
	ret
	.seh_endproc
	.globl	getArrayIndex
	.def	getArrayIndex;	.scl	2;	.type	32;	.endef
	.seh_proc	getArrayIndex
getArrayIndex:
	pushq	%rbp
	.seh_pushreg	%rbp
	movq	%rsp, %rbp
	.seh_setframe	%rbp, 0
	.seh_endprologue
	movq	%rcx, 16(%rbp)
	movl	%edx, 24(%rbp)
	cmpl	$9, 24(%rbp)
	jg	.L10
	cmpl	$0, 24(%rbp)
	js	.L10
	movl	24(%rbp), %eax
	cltq
	leaq	0(,%rax,4), %rdx
	movq	16(%rbp), %rax
	addq	%rdx, %rax
	movl	(%rax), %eax
	jmp	.L11
.L10:
	movl	$-1, %eax
.L11:
	popq	%rbp
	ret
	.seh_endproc
	.globl	setClassVar
	.def	setClassVar;	.scl	2;	.type	32;	.endef
	.seh_proc	setClassVar
setClassVar:
	pushq	%rbp
	.seh_pushreg	%rbp
	movq	%rsp, %rbp
	.seh_setframe	%rbp, 0
	.seh_endprologue
	movq	%rcx, 16(%rbp)
	movl	%edx, 24(%rbp)
	movl	%r8d, 32(%rbp)
	cmpl	$2, 24(%rbp)
	jg	.L14
	cmpl	$0, 24(%rbp)
	js	.L14
	movl	24(%rbp), %eax
	cltq
	leaq	0(,%rax,4), %rdx
	movq	16(%rbp), %rax
	addq	%rax, %rdx
	movl	32(%rbp), %eax
	movl	%eax, (%rdx)
.L14:
	nop
	popq	%rbp
	ret
	.seh_endproc
	.globl	getClassVar
	.def	getClassVar;	.scl	2;	.type	32;	.endef
	.seh_proc	getClassVar
getClassVar:
	pushq	%rbp
	.seh_pushreg	%rbp
	movq	%rsp, %rbp
	.seh_setframe	%rbp, 0
	.seh_endprologue
	movq	%rcx, 16(%rbp)
	movl	%edx, 24(%rbp)
	cmpl	$2, 24(%rbp)
	jg	.L16
	cmpl	$0, 24(%rbp)
	js	.L16
	movl	24(%rbp), %eax
	cltq
	leaq	0(,%rax,4), %rdx
	movq	16(%rbp), %rax
	addq	%rdx, %rax
	movl	(%rax), %eax
	jmp	.L17
.L16:
	movl	$-1, %eax
.L17:
	popq	%rbp
	ret
	.seh_endproc
	.ident	"GCC: (GNU) 5.4.0"
	.def	printf;	.scl	2;	.type	32;	.endef
	.def	malloc;	.scl	2;	.type	32;	.endef
