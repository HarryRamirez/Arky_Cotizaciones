import { ChangeDetectorRef, Component, EventEmitter, Input, OnInit, Output, signal } from '@angular/core';
import { FormsModule} from '@angular/forms';
import { ServiceService } from '../../../services/service.service';
import { ServiceInterface } from '../../../interfaces/service.interface';
import { QuotationItem } from '../../../interfaces/quotationUpdate.interface';
import { QuotationItemTemp } from '../../../interfaces/quotationItemTemp.interface';

@Component({
  selector: 'app-item-quot',
  standalone: true,
  imports: [FormsModule],
  templateUrl: './item-quot.component.html',
  styleUrl: './item-quot.component.scss'
})
export default class ItemQuotComponent implements OnInit{


  @Output() itemsForm = new EventEmitter<QuotationItemTemp[]>(); // Emitimos los ítems al componente padre
  @Output() totalUpdated = new EventEmitter<number>(); // Emitimos el total al componente padre
  items: QuotationItemTemp[] = []; // Lista de ítems con el campo temporal

  products: ServiceInterface[] = [];

  isChecked: boolean[] = [];

constructor(private serviceService: ServiceService, private cd: ChangeDetectorRef){

}

ngOnInit(): void {


  this.getProducts();
 };

 // Metodo para agregar un item
   addItem() {
    const newItem: QuotationItemTemp = {
      tempId: Date.now(), // Genera un tempId basado en el tiempo actual
      productId: null,
      description: '',
      quantity: 1,
      unitValue: 0,
      totalValue: 0
    };
    this.items.push(newItem);
    this.emitItems();
  }



    // Método para emitir los ítems actualizados al componente padre
    emitItems() {
      this.itemsForm.emit(this.items);
    }



    // Metodo para eliminar un item
  deleteItem(tempId: number) {
  console.log('Tipo de id:', typeof tempId);
    this.items = this.items.filter(item => item.tempId !== tempId);
    this.updateQuotationTotal(); // Llamamos a la función para recalcular el total general
    this.cd.detectChanges();
  }



// Metodo para cambiar campo del producto
cambiar(tempId: number){
    console.log(typeof tempId);
    this.isChecked[tempId] = !this.isChecked[tempId];
    
  }


  
    // Método para obtener las categorías de la base de datos
    getProducts(): void {
      this.serviceService.getAllServices().subscribe((data: ServiceInterface[]) => {
        this.products = data;
      });
    }



    

  // Método que se llama cuando se selecciona un producto en el select
  onProductSelected(item: any, productId: number): void {
    const selectedProduct = this.products.find(product => product.productId === +productId);

    if (selectedProduct) {
      // Rellenar los campos de descripción y precio unitario
      item.description = selectedProduct.description;
      item.unitValue = selectedProduct.net;

         // Calcular el valor total del ítem
    this.updateTotalValue(item); // Llamamos a este método para actualizar el valor total
        // Forzar la detección de cambios después de actualizar el item
    this.cd.detectChanges();

    }
  }





  // Método para actualizar el valor total cuando se cambia la cantidad
  updateTotalValue(item: any): void {
    item.totalValue = item.quantity * item.unitValue || 0;
    this.emitItems();
    this.updateQuotationTotal(); // Llamamos a este método para actualizar el total general
    this.cd.detectChanges();
  }



  // Metodo para actualizar el total de la cotizacion 
  updateQuotationTotal(): void {
    // Sumar todos los valores totales de los ítems
    const total = this.items.reduce((sum, item) => sum + (item.totalValue || 0), 0);
    // Emitir el total actualizado al componente padre
    this.totalUpdated.emit(total);
    this.cd.markForCheck();
  
  }


    // Método para mostrar los ítems en la consola
    mostrarItemsEnConsola() {
      console.log('Ítems actuales:', this.items);
    }



  // Método para resetear los ítems
  resetItems() {
    this.items = []; // Limpia los ítems
    this.emitItems(); // Notifica al componente padre que los ítems han cambiado
  }
}




