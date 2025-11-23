import { Component, OnInit, signal, ViewChild,} from '@angular/core';
import { FormsModule } from '@angular/forms';
import ItemQuotComponent from '../item-quot/item-quot.component';
import { Router, RouterLink } from '@angular/router';
import { ClientInterface } from '../../../interfaces/client.interface';
import { QuotationService } from '../../../services/quotation.service';
import { ClientService } from '../../../services/client.service';
import { ServiceService } from '../../../services/service.service';
import { ServiceInterface } from '../../../interfaces/service.interface';
import { DiscountService } from '../../../services/discount.service';
import { DiscountInterface } from '../../../interfaces/quotationFind.interface';
import { QuotationItem, QuotationUpdateInterface } from '../../../interfaces/quotationUpdate.interface';
import { QuotationItemTemp } from '../../../interfaces/quotationItemTemp.interface';
import { Modal } from 'bootstrap';


@Component({
  selector: 'app-add-quotation',
  standalone: true,
  imports: [RouterLink,FormsModule, ItemQuotComponent],
  templateUrl: './add-quotation.component.html',
  styleUrl: './add-quotation.component.scss'
})
export default class AddQuotationComponent implements OnInit{

   // Tu componente hijo
 @ViewChild(ItemQuotComponent) itemQuotComponent!: ItemQuotComponent;

neto: number = 0;  // Total neto de la cotización
totalNeto: number = 0;  // Total neto de la cotización
total: number = 0;  // Total neto de la cotización
selectedDiscount: number | null = null; // Porcentaje del descuento seleccionado
iva: number | null = null;
applyIva: boolean = false;

items: QuotationItem[] = [];

discounts: DiscountInterface[] = [];

customers: ClientInterface[] = [];

 public isChecked = signal (false);

 products: ServiceInterface[] = [];

 isCheckeds: boolean[] = [];

 

 quotation: QuotationUpdateInterface = {
  quotationId: 0,
  userId: 0,
  customerId: 0,
  discountId: 1,
  remitter: '',
  dateQuotation: new Date(),
  dateEvent: new Date(),
  reference: '',
  stateId: 0,
  net: 0,
  iva: 0,
  total: 0,
  items: [] 
};

 constructor(private quotationService: QuotationService, 
  private clientService: ClientService,
   private discountService: DiscountService,
   private serviceService: ServiceService,
   private router: Router
){}


 ngOnInit(): void {

  this.getCustomers();
  this.getDiscounts();
 };

  public cambiar(){
    this.isChecked.update(value => !value);
  }


    getCustomers(): void {
      this.clientService.getAllClients().subscribe((data: ClientInterface[]) => {
        this.customers = data;
      });
    }


        getDiscounts(): void {
          this.discountService.getAllDiscounts().subscribe((data: DiscountInterface[]) => {
            this.discounts = data;
          });
        }
  

  // Método que recibe el total de la cotización desde el componente de ítems
  onNetoUpdated(neto: number): void {
    this.neto = neto;
    
    console.log('Total actualizado en el padre:', neto);  // Para depuración
  }




onDiscountSelected(event: Event): void {
  const selectElement = event.target as HTMLSelectElement;
  const discountId = +selectElement.value;  // Obtenemos el discountId seleccionado

  const selectedDiscount = this.discounts.find(discount => discount.discountId === discountId);

  if (selectedDiscount) {
    this.selectedDiscount = selectedDiscount.percentage;
    this.applyDiscount();
 
  }else{
    this.selectedDiscount = 1;

  }
  console.log("descuento: ",this.selectedDiscount);
}

    // Metodo para aplicar el descuento
    applyDiscount(): void {
      const discountFactor = (100 - this.selectedDiscount) / 100;  // Convierte el porcentaje a un factor (ej: 10% => 0.9)
      this.totalNeto = this.total * discountFactor;  // Aplica el descuento al total neto
    }


  
  // Actualizar el total neto (sin descuento)
  onTotalUpdated(total: number): void {
    this.total = total;
    this.totalNeto = total;
    this.applyDiscount();  // Aplica el descuento si ya hay uno seleccionado
  }



    // Método para seleccionar el IVA
    onIvaSelected(event: Event): void {
      const checkbox = event.target as HTMLInputElement;
      this.applyIva = checkbox.checked; // Actualiza el estado del checkbox

      if(this.applyIva){
        this.iva = 0.19;
      }else{
        this.iva = 0;
      }
      console.log("iva: ",this.iva);
      this.applyIvaCalculation(); // Aplica o elimina el IVA
    }
  



    // Método para aplicar el IVA
    applyIvaCalculation(): void {
      if (this.applyIva) {
        this.totalNeto = this.totalNeto * (1 + this.iva); // Aplica el IVA al total neto
      } else {
        this.totalNeto = this.total * ((100 - this.selectedDiscount) / 100); // Restablece al total neto sin IVA
        this.applyDiscount(); // Aplica nuevamente el descuento si es necesario
      }
    }


    // Obtener los items del componenete item
    onItems(itemsFromChild: QuotationItemTemp[]) {
      this.quotation.items = itemsFromChild.map(item => ({
        productId: item.productId ?? 0,
        description: item.description ?? '',
        quantity: item.quantity ?? 1,
        unitValue: item.unitValue ?? 0,
        totalValue: item.totalValue ?? 0
      }));
      console.log('Ítems preparados para guardar:', this.quotation.items);
    }




    

    
    
    // Metodo para guardar cotizacion
    saveQuotation() {
      this.quotation.userId = Number(localStorage.getItem('userId'));
      this.quotation.net = this.total;
      this.quotation.total = this.totalNeto;
      this.quotation.iva = this.iva || 0;
      console.log('Guardando cotización:', this.quotation);  // Verifica los datos antes de enviarlos
    
      if (!this.quotation.customerId || this.quotation.items.length === 0) {
        console.error('Faltan datos obligatorios para guardar la cotización');
        return; // No hacer la llamada si faltan datos importantes
      }
    
      this.quotationService.saveQuotation(this.quotation).subscribe({
        next: (response) => {
          console.log('Cotización guardada con éxito:', response);
          // Aquí puedes hacer algo más, como redirigir o mostrar un mensaje
         // Mostrar el modal de éxito
          const modalElement = document.getElementById('staticBackdrop');
          const modal = new Modal(modalElement!);
          modal.show();
           
        // Ocultar el modal después de 3 segundos
          setTimeout(() => {
             modal.hide();
          }, 2000);   
          
          this.resetQuotation();
        },
        error: (error) => {
          console.error('Error al guardar la cotización:', error);
        }
      });
    }


    // Reseteamos las cotizaciones en el formulario
    resetQuotation() {
      // Restablece la cotización a sus valores por defecto
      this.quotation = {
        quotationId: 0,
        userId: 0,
        customerId: 0,
        discountId: 0,
        remitter: '',
        dateQuotation: new Date(),
        dateEvent: new Date(),
        reference: '',
        stateId: 0,
        net: 0,
        iva: 0,
        total: 0,
        items: [] // Vacía la lista de ítems
      };
    
      // Restablece el total, descuento y IVA
      const neto = this.quotation.net = this.total;
      this.neto = 0;
      this.totalNeto = 0;
      this.selectedDiscount = null;
      this.iva = null;
      this.applyIva = false;
    
    // Limpia los ítems en el componente hijo
    if (this.itemQuotComponent) {
      this.itemQuotComponent.resetItems();  // Llama al método para limpiar ítems
    }
    }


}
