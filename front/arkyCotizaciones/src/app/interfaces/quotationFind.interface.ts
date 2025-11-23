import { ClientInterface } from "./client.interface";
import { ItemInterface } from "./quotation.interface";
import { StateInterface } from "./state.interface";

export interface QuotationFindInterface{

    quotationId: number;
    user: UserInterface;
    customer: ClientInterface;
    discount: DiscountInterface;
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

export interface UserInterface{
    userId: number;
    firstname: string;
    lastname: string;
    email: string;
    password: string;
    role: RoleInterface;
}
export interface RoleInterface{
    roleId: number;
    name: string;
}

export interface DiscountInterface{
    discountId: number;
    percentage: number;
}
