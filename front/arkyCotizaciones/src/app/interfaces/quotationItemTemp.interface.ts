import { QuotationItem } from "./quotationUpdate.interface";

// Definimos una nueva interfaz para el uso temporal en el frontend
export interface QuotationItemTemp extends QuotationItem {
    tempId: number; // Campo temporal que utilizaremos para identificar los ítems en el frontend
  }