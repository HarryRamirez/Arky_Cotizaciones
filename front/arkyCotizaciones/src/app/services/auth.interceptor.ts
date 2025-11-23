import { HttpInterceptorFn } from '@angular/common/http';

export const authInterceptor: HttpInterceptorFn = (req, next) => {
  const token = localStorage.getItem('jwtToken'); // Obtener el token de localStorage

  // Si el token existe, clonamos la solicitud y le añadimos el token en la cabecera
  const authReq = token ? req.clone({
    setHeaders: { Authorization: `Bearer ${token}`}
  }) : req;

  return next(authReq); // Enviar la solicitud clonada o la original si no hay token
};