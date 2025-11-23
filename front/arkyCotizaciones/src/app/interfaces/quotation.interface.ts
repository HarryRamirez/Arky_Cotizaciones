import { StateInterface } from "./state.interface";

export interface QuotationInterface{

    quotationId: number;
    user: string;
    customer: string;
    discount: number;
    remitter: string;
    dateQuotation: Date;
    dateEvent: Date;
    reference: string;
    state: StateInterface;
    net: number;
    iva: number;
    total: number;
    items: ItemInterface[];

}


export interface ItemInterface{

    product: string;
    description: string;
    quantity: number;
    valueUnit: number;
    valueTotal: number;
}


export interface SearchInterface{

    quotationId: number;
    user: string;
    customer: string;
    discount: number;
    remitter: string;
    dateQuotation: Date;
    dateEvent: Date;
    reference: string;
    state: number;
    net: number;
    iva: number;
    total: number;
    items: ItemInterface[];

}