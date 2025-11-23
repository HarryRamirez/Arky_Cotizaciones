import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { QuotationInterface } from '../interfaces/quotation.interface';
import { Observable } from 'rxjs';
import { UpdateState } from '../interfaces/updateState.interface';
import { QuotationFindInterface } from '../interfaces/quotationFind.interface';
import { QuotationUpdateInterface } from '../interfaces/quotationUpdate.interface';


@Injectable({
  providedIn: 'root'
})
export class QuotationService {

  private apiUrl = 'http://localhost:8080/arky/app/quotation'
  
  constructor(private http: HttpClient) { }

  getAllQuotations(): Observable<QuotationInterface[]> {
    return this.http.get<QuotationInterface[]>(`${this.apiUrl}/all`);
  }


  updateQuotationState(quotationId: number, updateState: UpdateState): Observable<QuotationInterface> {
    return this.http.put<QuotationInterface>(`${this.apiUrl}/update/state/${quotationId}`, updateState);
  }


    // Método para obtener una cotización por su ID
    getQuotationById(id: number): Observable<QuotationFindInterface> {
      return this.http.get<QuotationFindInterface>(`${this.apiUrl}/${id}`);
    }




deleteQuotation(id: number): Observable<void> {
  return this.http.delete<void>(`${this.apiUrl}/delete/${id}`);
}


 // Método para guardar la cotización
saveQuotation(quotation: QuotationUpdateInterface): Observable<any> {
   return this.http.post<any>(`${this.apiUrl}/save`, quotation);
}

searchQuotationByName(name: string): Observable<QuotationInterface[]> {
  return this.http.get<QuotationInterface[]>(`${this.apiUrl}/search?name=${name}`);
}

  // Método para obtener el conteo de cotizaciones por estado
countQuotationsByState(stateId: number): Observable<number> {
  const url = `${this.apiUrl}/count?stateId=${stateId}`;
  return this.http.get<number>(url);
}
}
