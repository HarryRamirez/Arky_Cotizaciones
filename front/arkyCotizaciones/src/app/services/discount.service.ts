import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { DiscountInterface } from '../interfaces/quotationFind.interface';
import { Observable } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class DiscountService {

  private apiUrl = 'http://localhost:8080/arky/app/discount';

  constructor(private http: HttpClient) { }


  getAllDiscounts(): Observable<DiscountInterface[]> {
    return this.http.get<DiscountInterface[]>(`${this.apiUrl}/all`);
  }

}
