import { Component, OnInit } from '@angular/core';
import { FormsModule } from '@angular/forms';
import { Chart, ChartType } from 'chart.js/auto';
import { QuotationService } from '../../../services/quotation.service';


@Component({
  selector: 'app-home',
  standalone: true,
  imports: [],
  templateUrl: './home.component.html',
  styleUrl: './home.component.scss'
})
export default class HomeComponent implements OnInit{

  acceptedCount: number = 0;
  pendingCount: number = 0;
  rejectedCount: number = 0;


  public chart1: Chart;
  public chart2: Chart;

  constructor(private quotationService: QuotationService){}



  ngOnInit(): void {

    this.getQuotationCounts();
  
    
    // Metodos para los dos graficos
    const data1 = {
      labels: ['Aceptadas', 'Pendientes', 'Recahzadas'],
      datasets: [{
        label: 'total',
        data: [500, 150, 90],
        backgroundColor: [
          'rgba(75, 192, 192, 0.2)',
          'rgba(255, 205, 86, 0.2)',
          'rgba(255, 99, 132, 0.2)'
        ],
        borderColor: [
          'rgb(75, 192, 192)',
          'rgb(255, 205, 86)',
          'rgb(255, 99, 132)'
        ],
        borderWidth: 1
      }]
  }


  this.chart1 = new Chart('chart1', {
    type: 'bar' as ChartType,
    data: data1,
    options: {
      responsive: true,
      scales: {
        y: {
          beginAtZero: true
        }
      }
    }
  });



  const data2 = {
    labels: ['Aceptadas', 'Pendientes', 'Recahzadas'],
    datasets: [{
      label: 'total',
      data: [1150, 600, 400],
      backgroundColor: [
        'rgba(75, 192, 192, 0.2)',
        'rgba(255, 205, 86, 0.2)',
        'rgba(255, 99, 132, 0.2)'
      ],
      borderColor: [
        'rgb(75, 192, 192)',
        'rgb(255, 205, 86)',
        'rgb(255, 99, 132)'
      ],
      borderWidth: 1
    }]
}

this.chart2 = new Chart('chart2', {
  type: 'bar' as ChartType,
  data: data2,
  options: {
    responsive: true,
    scales: {
      y: {
        beginAtZero: true
      }
    }
  }
});


}





    // Método para obtener los conteos de cotizaciones
    getQuotationCounts(): void {

      this.quotationService.countQuotationsByState(1).subscribe(count => {
        this.acceptedCount = count;
      });
  

      this.quotationService.countQuotationsByState(2).subscribe(count => {
        this.pendingCount = count;
      });
  

      this.quotationService.countQuotationsByState(3).subscribe(count => {
        this.rejectedCount = count;
      });
    }






}