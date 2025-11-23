export interface QuotationUpdateInterface{

    quotationId: number;
    userId: number;
    customerId: number;
    discountId: number;
    remitter: string;
    dateQuotation: Date;
    dateEvent: Date;
    reference: string;
    stateId: number;
    net: number;
    iva: number;
    total: number;
    items: QuotationItem[];
}

export interface QuotationItem {
    productId: number;
    description: string;
    quantity: number;
    unitValue: number;
    totalValue: number;
  }
