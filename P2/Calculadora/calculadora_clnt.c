/*
 * Please do not edit this file.
 * It was generated using rpcgen.
 */

#include <memory.h> /* for memset */
#include "calculadora.h"

/* Default timeout can be changed using clnt_control() */
static struct timeval TIMEOUT = { 25, 0 };

t_vector *
suma_2(t_vector a, t_vector b,  CLIENT *clnt)
{
	suma_2_argument arg;
	static t_vector clnt_res;

	memset((char *)&clnt_res, 0, sizeof(clnt_res));
	arg.a = a;
	arg.b = b;
	if (clnt_call (clnt, SUMA, (xdrproc_t) xdr_suma_2_argument, (caddr_t) &arg,
		(xdrproc_t) xdr_t_vector, (caddr_t) &clnt_res,
		TIMEOUT) != RPC_SUCCESS) {
		return (NULL);
	}
	return (&clnt_res);
}

t_vector *
resta_2(t_vector a, t_vector b,  CLIENT *clnt)
{
	resta_2_argument arg;
	static t_vector clnt_res;

	memset((char *)&clnt_res, 0, sizeof(clnt_res));
	arg.a = a;
	arg.b = b;
	if (clnt_call (clnt, RESTA, (xdrproc_t) xdr_resta_2_argument, (caddr_t) &arg,
		(xdrproc_t) xdr_t_vector, (caddr_t) &clnt_res,
		TIMEOUT) != RPC_SUCCESS) {
		return (NULL);
	}
	return (&clnt_res);
}

t_vector *
multp_2(t_vector a, t_vector b,  CLIENT *clnt)
{
	multp_2_argument arg;
	static t_vector clnt_res;

	memset((char *)&clnt_res, 0, sizeof(clnt_res));
	arg.a = a;
	arg.b = b;
	if (clnt_call (clnt, MULTP, (xdrproc_t) xdr_multp_2_argument, (caddr_t) &arg,
		(xdrproc_t) xdr_t_vector, (caddr_t) &clnt_res,
		TIMEOUT) != RPC_SUCCESS) {
		return (NULL);
	}
	return (&clnt_res);
}

t_vector *
prodv_2(t_vector a, t_vector b,  CLIENT *clnt)
{
	prodv_2_argument arg;
	static t_vector clnt_res;

	memset((char *)&clnt_res, 0, sizeof(clnt_res));
	arg.a = a;
	arg.b = b;
	if (clnt_call (clnt, PRODV, (xdrproc_t) xdr_prodv_2_argument, (caddr_t) &arg,
		(xdrproc_t) xdr_t_vector, (caddr_t) &clnt_res,
		TIMEOUT) != RPC_SUCCESS) {
		return (NULL);
	}
	return (&clnt_res);
}

int *
sumar_1(int a, int b,  CLIENT *clnt)
{
	sumar_1_argument arg;
	static int clnt_res;

	memset((char *)&clnt_res, 0, sizeof(clnt_res));
	arg.a = a;
	arg.b = b;
	if (clnt_call (clnt, SUMAR, (xdrproc_t) xdr_sumar_1_argument, (caddr_t) &arg,
		(xdrproc_t) xdr_int, (caddr_t) &clnt_res,
		TIMEOUT) != RPC_SUCCESS) {
		return (NULL);
	}
	return (&clnt_res);
}

int *
restar_1(int a, int b,  CLIENT *clnt)
{
	restar_1_argument arg;
	static int clnt_res;

	memset((char *)&clnt_res, 0, sizeof(clnt_res));
	arg.a = a;
	arg.b = b;
	if (clnt_call (clnt, RESTAR, (xdrproc_t) xdr_restar_1_argument, (caddr_t) &arg,
		(xdrproc_t) xdr_int, (caddr_t) &clnt_res,
		TIMEOUT) != RPC_SUCCESS) {
		return (NULL);
	}
	return (&clnt_res);
}

int *
multp_1(int a, int b,  CLIENT *clnt)
{
	multp_1_argument arg;
	static int clnt_res;

	memset((char *)&clnt_res, 0, sizeof(clnt_res));
	arg.a = a;
	arg.b = b;
	if (clnt_call (clnt, MULTP, (xdrproc_t) xdr_multp_1_argument, (caddr_t) &arg,
		(xdrproc_t) xdr_int, (caddr_t) &clnt_res,
		TIMEOUT) != RPC_SUCCESS) {
		return (NULL);
	}
	return (&clnt_res);
}

int *
divid_1(int a, int b,  CLIENT *clnt)
{
	divid_1_argument arg;
	static int clnt_res;

	memset((char *)&clnt_res, 0, sizeof(clnt_res));
	arg.a = a;
	arg.b = b;
	if (clnt_call (clnt, DIVID, (xdrproc_t) xdr_divid_1_argument, (caddr_t) &arg,
		(xdrproc_t) xdr_int, (caddr_t) &clnt_res,
		TIMEOUT) != RPC_SUCCESS) {
		return (NULL);
	}
	return (&clnt_res);
}

int *
power_1(int a, int b,  CLIENT *clnt)
{
	power_1_argument arg;
	static int clnt_res;

	memset((char *)&clnt_res, 0, sizeof(clnt_res));
	arg.a = a;
	arg.b = b;
	if (clnt_call (clnt, POWER, (xdrproc_t) xdr_power_1_argument, (caddr_t) &arg,
		(xdrproc_t) xdr_int, (caddr_t) &clnt_res,
		TIMEOUT) != RPC_SUCCESS) {
		return (NULL);
	}
	return (&clnt_res);
}

int *
modulo_1(int a, int b,  CLIENT *clnt)
{
	modulo_1_argument arg;
	static int clnt_res;

	memset((char *)&clnt_res, 0, sizeof(clnt_res));
	arg.a = a;
	arg.b = b;
	if (clnt_call (clnt, MODULO, (xdrproc_t) xdr_modulo_1_argument, (caddr_t) &arg,
		(xdrproc_t) xdr_int, (caddr_t) &clnt_res,
		TIMEOUT) != RPC_SUCCESS) {
		return (NULL);
	}
	return (&clnt_res);
}
